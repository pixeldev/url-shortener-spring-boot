package io.github.pixeldev.urlshortener.application.port.out;

import io.github.pixeldev.urlshortener.domain.model.UserModel;

public interface UserRepository {
  UserModel findByIdSafe(final String id);
}
