package io.github.pixeldev.urlshortener.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;
import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UrlShortenedEntity;
import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UserEntity;

@Component
public class UrlShortenedMapper {
  private final UserMapper userMapper;

  public UrlShortenedMapper(final UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public UrlShortenedEntity toEntity(final UrlShortenedModel model) {
    final UserModel userModel = model.getUser();
    final UserEntity userEntity = userModel == null ? null : this.userMapper.toEntity(userModel);

    return new UrlShortenedEntity(
        model.getId(),
        userEntity,
        model.isPrivate(),
        model.getOriginalUrl(),
        model.getExpirationDate(),
        model.getCreatedAt(),
        model.getUpdatedAt());
  }

  public UrlShortenedModel toModel(final UrlShortenedEntity entity) {
    final UserEntity userEntity = entity.getUser();

    final UserModel userModel = userEntity == null ? null : this.userMapper.toModel(userEntity);

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
