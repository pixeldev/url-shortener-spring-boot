package io.github.pixeldev.urlshortener.infrastructure.persistence.entity;

import java.time.LocalDateTime;

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

  @Column(name = "expiration_date")
  @Nullable
  private LocalDateTime expirationDate;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public UrlShortenedEntity(
      final String id,
      @Nullable final UserEntity user,
      final boolean isPrivate,
      final String originalUrl,
      @Nullable final LocalDateTime expirationDate,
      final LocalDateTime createdAt) {
    this.id = id;
    this.user = user;
    this.isPrivate = isPrivate;
    this.originalUrl = originalUrl;
    this.expirationDate = expirationDate;
    this.createdAt = createdAt;
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
  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(@Nullable final LocalDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = java.time.LocalDateTime.now();
  }
}
