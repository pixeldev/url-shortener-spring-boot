package io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity.UrlShortenedEntity;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity.UserEntity;

@Component
public class UrlShortenedMapper {
  private final UserMapper userMapper;

  public UrlShortenedMapper(final UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public UrlShortenedEntity toEntity(final UrlShortenedModel model) {
    final UserEntity userEntity = model.getUser().map(this.userMapper::toEntity).orElse(null);

    return new UrlShortenedEntity(
        model.getId(),
        userEntity,
        model.isPrivate(),
        model.getOriginalUrl(),
        model.getExpirationDate().orElse(null),
        model.getCreatedAt(),
        model.getUpdatedAt());
  }

  public UrlShortenedModel toModel(final UrlShortenedEntity entity) {
    final UserModel userModel =
        Optional.ofNullable(entity.getUser()).map(this.userMapper::toModel).orElse(null);

    return new UrlShortenedModel(
        entity.getId(),
        entity.getCreatedAt(),
        userModel,
        entity.isPrivate(),
        entity.getOriginalUrl(),
        entity.getExpiresAt(),
        entity.getUpdatedAt());
  }
}
