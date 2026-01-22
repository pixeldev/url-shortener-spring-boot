package io.github.pixeldev.urlshortener.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;
import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UrlShortenedEntity;
import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UserEntity;

@Component
public class UrlShortenedMapper {
  public UrlShortenedEntity toEntity(final UrlShortenedModel model) {
    final UserModel userModel = model.getUser();
    final UserEntity userEntity =
        userModel == null
            ? null
            : new UserEntity(userModel.getId(), userModel.getFullName(), userModel.getCreatedAt());

    return new UrlShortenedEntity(
        model.getId(),
        userEntity,
        model.isPrivate(),
        model.getOriginalUrl(),
        model.getExpirationDate(),
        model.getCreatedAt());
  }

  public UrlShortenedModel toModel(final UrlShortenedEntity entity) {
    final UserEntity userEntity = entity.getUser();

    final UserModel userModel =
        userEntity == null
            ? null
            : new UserModel(
                userEntity.getId(), userEntity.getFullName(), userEntity.getCreatedAt());

    return new UrlShortenedModel(
        entity.getId(),
        entity.getCreatedAt(),
        userModel,
        entity.isPrivate(),
        entity.getOriginalUrl(),
        entity.getExpirationDate());
  }
}
