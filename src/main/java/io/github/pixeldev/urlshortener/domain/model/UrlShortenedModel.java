package io.github.pixeldev.urlshortener.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.lang.Nullable;

public class UrlShortenedModel {
  private final String id;
  private final LocalDateTime createdAt;
  @Nullable private UserModel user;
  private boolean isPrivate;
  private String originalUrl;
  @Nullable private LocalDateTime expirationDate;

  public UrlShortenedModel(
      final String id,
      final LocalDateTime createdAt,
      @Nullable final UserModel user,
      final boolean isPrivate,
      final String originalUrl,
      @Nullable final LocalDateTime expirationDate) {
    this.id = id;
    this.createdAt = createdAt;
    this.user = user;
    this.isPrivate = isPrivate;
    this.originalUrl = originalUrl;
    this.expirationDate = expirationDate;
  }

  public String getId() {
    return id;
  }

  public LocalDateTime getCreatedAt() {
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
  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(@Nullable final LocalDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public boolean isExpired() {
    return expirationDate != null && expirationDate.isBefore(LocalDateTime.now());
  }

  public boolean isOwner(final @Nullable Long userId) {
    return user != null && Objects.equals(user.getId(), userId);
  }
}
