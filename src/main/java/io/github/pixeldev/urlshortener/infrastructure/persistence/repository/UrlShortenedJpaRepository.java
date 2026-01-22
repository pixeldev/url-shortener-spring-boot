package io.github.pixeldev.urlshortener.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pixeldev.urlshortener.infrastructure.persistence.entity.UrlShortenedEntity;

public interface UrlShortenedJpaRepository extends JpaRepository<UrlShortenedEntity, String> {
  @EntityGraph(attributePaths = {"user"})
  Optional<UrlShortenedEntity> findWithUserById(String id);
}
