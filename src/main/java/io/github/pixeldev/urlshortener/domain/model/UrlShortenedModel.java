package io.github.pixeldev.urlshortener.domain.model;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import io.github.pixeldev.urlshortener.domain.validation.Ensure;

public class UrlShortenedModel {
  private final String id;
  private final Instant createdAt;
  private final UserModel user;
  private boolean isPrivate;
  private String originalUrl;
  private Instant expirationDate;
  private Instant updatedAt;

  public UrlShortenedModel(
      final String id,
      final Instant createdAt,
      final UserModel user,
      final boolean isPrivate,
      final String originalUrl,
      final Instant expirationDate,
      final Instant updatedAt) {
    this.id = Ensure.notBlank(id, "ID cannot be blank or null");
    this.createdAt = createdAt;
    this.user = user;
    this.isPrivate = isPrivate;
    this.originalUrl = Ensure.notNull(originalUrl, "Original URL cannot be null");
    this.expirationDate = expirationDate;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Optional<UserModel> getUser() {
    return Optional.ofNullable(user);
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(final boolean aPrivate) {
    isPrivate = aPrivate;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(final String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public Optional<Instant> getExpirationDate() {
    return Optional.ofNullable(expirationDate);
  }

  public void setExpirationDate(final Instant expirationDate) {
    this.expirationDate = expirationDate;
  }

  public boolean isExpired(final Clock clock) {
    return expirationDate != null && expirationDate.isBefore(Instant.now(clock));
  }

  public boolean hasAccess(final String userId) {
    return !this.isPrivate || (user != null && Objects.equals(user.getId(), userId));
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(final Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
