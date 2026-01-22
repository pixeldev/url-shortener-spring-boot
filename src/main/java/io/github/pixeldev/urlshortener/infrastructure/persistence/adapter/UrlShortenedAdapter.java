package io.github.pixeldev.urlshortener.infrastructure.persistence.adapter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.application.port.adapter.UrlShortenedPort;
import io.github.pixeldev.urlshortener.domain.exception.ModelNotFoundException;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UrlShortenedEntity;
import io.github.pixeldev.urlshortener.infrastructure.persistence.mapper.UrlShortenedMapper;
import io.github.pixeldev.urlshortener.infrastructure.persistence.repository.UrlShortenedJpaRepository;

@Component
public class UrlShortenedAdapter implements UrlShortenedPort {
  private final UrlShortenedMapper mapper;
  private final UrlShortenedJpaRepository repository;

  public UrlShortenedAdapter(
      final UrlShortenedMapper mapper, final UrlShortenedJpaRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public UrlShortenedModel save(final UrlShortenedModel model) {
    final UrlShortenedEntity entity = this.mapper.toEntity(model);
    final UrlShortenedEntity savedEntity = this.repository.save(entity);
    return this.mapper.toModel(savedEntity);
  }

  @Override
  public @Nullable UrlShortenedModel findById(final String id) {
    return this.repository.findById(id).map(this.mapper::toModel).orElse(null);
  }

  @Override
  public UrlShortenedModel findByIdWithUserSafe(final String id) {
    return this.repository
        .findWithUserById(id)
        .map(this.mapper::toModel)
        .orElseThrow(() -> ModelNotFoundException.create("UrlShortened", id));
  }
}
