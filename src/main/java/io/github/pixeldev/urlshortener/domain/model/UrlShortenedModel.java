package io.github.pixeldev.urlshortener.domain.model;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

import org.springframework.lang.Nullable;

public class UrlShortenedModel {
  private final String id;
  private final Instant createdAt;
  @Nullable private UserModel user;
  private boolean isPrivate;
  private String originalUrl;
  @Nullable private Instant expirationDate;
  private Instant updatedAt;

  public UrlShortenedModel(
      final String id,
      final Instant createdAt,
      @Nullable final UserModel user,
      final boolean isPrivate,
      final String originalUrl,
      @Nullable final Instant expirationDate,
      final Instant updatedAt) {
    this.id = id;
    this.createdAt = createdAt;
    this.user = user;
    this.isPrivate = isPrivate;
    this.originalUrl = originalUrl;
    this.expirationDate = expirationDate;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  @Nullable
  public UserModel getUser() {
    return user;
  }

  public void setUser(@Nullable final UserModel user) {
    this.user = user;
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

  @Nullable
  public Instant getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(@Nullable final Instant expirationDate) {
    this.expirationDate = expirationDate;
  }

  public boolean isExpired(final Clock clock) {
    return expirationDate != null && expirationDate.isBefore(Instant.now(clock));
  }

  public boolean hasAccess(final @Nullable String userId) {
    return !this.isPrivate || (user != null && Objects.equals(user.getId(), userId));
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(final Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
