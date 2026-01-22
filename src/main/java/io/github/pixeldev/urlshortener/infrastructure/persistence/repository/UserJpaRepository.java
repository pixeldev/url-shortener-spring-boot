package io.github.pixeldev.urlshortener.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {}
