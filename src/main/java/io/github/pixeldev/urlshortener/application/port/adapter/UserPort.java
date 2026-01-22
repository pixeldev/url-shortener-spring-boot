package io.github.pixeldev.urlshortener.application.port.adapter;

import io.github.pixeldev.urlshortener.domain.model.UserModel;

public interface UserPort {
  UserModel findByIdSafe(long id);
}
