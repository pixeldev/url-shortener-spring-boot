package io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.application.port.out.UserRepository;
import io.github.pixeldev.urlshortener.domain.exception.ModelNotFoundException;
import io.github.pixeldev.urlshortener.domain.model.UserModel;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.mapper.UserMapper;
import io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.repository.UserJpaRepository;

@Component
public class JpaUserAdapter implements UserRepository {
  private final UserMapper mapper;
  private final UserJpaRepository repository;

  public JpaUserAdapter(final UserMapper mapper, final UserJpaRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public UserModel findByIdSafe(final String id) {
    return this.repository
        .findById(id)
        .map(this.mapper::toModel)
        .orElseThrow(() -> ModelNotFoundException.create("User", String.valueOf(id)));
  }
}
