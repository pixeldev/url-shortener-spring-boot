package io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence;

import java.time.Duration;

import org.slf4j.MDC;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.application.port.out.UrlShortenedRepository;
import io.github.pixeldev.urlshortener.domain.exception.ModelNotFoundException;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity.UrlShortenedCacheDto;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity.UrlShortenedEntity;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.mapper.UrlShortenedMapper;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.repository.UrlShortenedJpaRepository;
import io.github.pixeldev.urlshortener.infrastructure.config.RedisCacheConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JpaUrlShortenedAdapter implements UrlShortenedRepository {
  private final UrlShortenedMapper mapper;
  private final RedisTemplate<String, Object> redisTemplate;
  private final UrlShortenedJpaRepository repository;

  public JpaUrlShortenedAdapter(
      final UrlShortenedMapper mapper,
      final RedisTemplate<String, Object> redisTemplate,
      final UrlShortenedJpaRepository repository) {
    this.mapper = mapper;
    this.redisTemplate = redisTemplate;
    this.repository = repository;
  }

  @Override
  public UrlShortenedModel save(final UrlShortenedModel model) {
    final UrlShortenedEntity entity = this.mapper.toEntity(model);
    final UrlShortenedEntity savedEntity = this.repository.save(entity);

    final String cacheKey = RedisCacheConfig.URLS_CACHE + "::" + model.getId();
    this.redisTemplate.delete(cacheKey);

    return this.mapper.toModel(savedEntity);
  }

  @Override
  public @Nullable UrlShortenedModel findById(final String id) {
    return this.repository.findById(id).map(this.mapper::toModel).orElse(null);
  }

  @Override
  public UrlShortenedModel findByIdWithUserSafe(final String id) {
    try (MDC.MDCCloseable ignored = MDC.putCloseable("urlId", id)) {
      log.debug("Attempting to find URL in cache");

      String cacheKey = RedisCacheConfig.URLS_CACHE + "::" + id;
      try {
        Object cachedValue = this.redisTemplate.opsForValue().get(cacheKey);
        if (cachedValue != null) {
          log.info("Cache hit for URL ID");
          return ((UrlShortenedCacheDto) cachedValue).toDomain();
        }
      } catch (Exception e) {
        log.error("Failed to connect to Redis while fetching URL", e);
      }

      final UrlShortenedModel model =
          this.repository
              .findWithUserById(id)
              .map(this.mapper::toModel)
              .orElseThrow(() -> ModelNotFoundException.create("UrlShortened", id));

      try {
        UrlShortenedCacheDto cacheDto = UrlShortenedCacheDto.fromModel(model);
        this.redisTemplate.opsForValue().set(cacheKey, cacheDto, Duration.ofHours(24));
      } catch (Exception e) {
        log.error("Failed to save URL to Redis cache", e);
      }
      return model;
    }
  }
}
