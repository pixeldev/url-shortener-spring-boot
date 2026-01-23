package io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity;

import java.time.Instant;

import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;

public class UrlShortenedCacheDto {
  private String id;
  private String originalUrl;
  private Instant createdAt;
  private Instant expirationDate;
  private boolean isPrivate;
  private UserCacheDto user;

  public UrlShortenedCacheDto() {}

  public static UrlShortenedCacheDto fromModel(final UrlShortenedModel model) {
    final UrlShortenedCacheDto dto = new UrlShortenedCacheDto();
    dto.setId(model.getId());
    dto.setOriginalUrl(model.getOriginalUrl());
    dto.setCreatedAt(model.getCreatedAt());
    dto.setExpirationDate(model.getExpirationDate().orElse(null));
    dto.setPrivate(model.isPrivate());
    model.getUser().ifPresent(u -> dto.setUser(UserCacheDto.fromModel(u)));
    return dto;
  }

  public UrlShortenedModel toDomain() {
    final UserModel userModel = this.user != null ? this.user.toDomain() : null;

    return new UrlShortenedModel(
        this.id,
        this.createdAt,
        userModel,
        this.isPrivate,
        this.originalUrl,
        this.expirationDate,
        null);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Instant expirationDate) {
    this.expirationDate = expirationDate;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
  }

  public UserCacheDto getUser() {
    return user;
  }

  public void setUser(UserCacheDto user) {
    this.user = user;
  }
}
