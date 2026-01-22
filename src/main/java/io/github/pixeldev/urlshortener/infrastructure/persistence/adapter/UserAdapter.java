package io.github.pixeldev.urlshortener.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.application.port.adapter.UserPort;
import io.github.pixeldev.urlshortener.domain.exception.ModelNotFoundException;
import io.github.pixeldev.urlshortener.domain.model.UserModel;
import io.github.pixeldev.urlshortener.infrastructure.persistence.mapper.UserMapper;
import io.github.pixeldev.urlshortener.infrastructure.persistence.repository.UserJpaRepository;

@Component
public class UserAdapter implements UserPort {
  private final UserMapper mapper;
  private final UserJpaRepository repository;

  public UserAdapter(final UserMapper mapper, final UserJpaRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public UserModel findByIdSafe(final long id) {
    return this.repository
        .findById(id)
        .map(this.mapper::toModel)
        .orElseThrow(() -> ModelNotFoundException.create("User", String.valueOf(id)));
  }
}
