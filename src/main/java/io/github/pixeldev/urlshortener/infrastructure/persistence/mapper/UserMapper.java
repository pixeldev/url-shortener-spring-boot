package io.github.pixeldev.urlshortener.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.domain.model.UserModel;
import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UserEntity;

@Component
public class UserMapper {
  public UserEntity toEntity(final UserModel model) {
    return new UserEntity(model.getId(), model.getFullName(), model.getCreatedAt());
  }

  public UserModel toModel(final UserEntity entity) {
    return new UserModel(entity.getId(), entity.getCreatedAt(), entity.getFullName());
  }
}
