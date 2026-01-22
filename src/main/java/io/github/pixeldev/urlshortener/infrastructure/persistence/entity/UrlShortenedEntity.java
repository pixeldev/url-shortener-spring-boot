package io.github.pixeldev.urlshortener.infrastructure.persistence.entity;

import java.time.Instant;

import org.springframework.lang.Nullable;

import jakarta.persistence.*;

@Entity
@Table(name = "urls_shortened")
public class UrlShortenedEntity {
  @Id private String id;

  @Nullable
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Column(name = "is_private", nullable = false)
  private boolean isPrivate;

  @Column(name = "original_url", nullable = false)
  private String originalUrl;

  @Column(name = "expires_at")
  @Nullable
  private Instant expiresAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  public UrlShortenedEntity(
      final String id,
      @Nullable final UserEntity user,
      final boolean isPrivate,
      final String originalUrl,
      @Nullable final Instant expiresAt,
      final Instant createdAt,
      final Instant updatedAt) {
    this.id = id;
    this.user = user;
    this.isPrivate = isPrivate;
    this.originalUrl = originalUrl;
    this.expiresAt = expiresAt;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public UrlShortenedEntity() {}

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public @Nullable UserEntity getUser() {
    return user;
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
  public Instant getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(@Nullable final Instant expirationDate) {
    this.expiresAt = expirationDate;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
