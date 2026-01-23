package io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity;

import java.time.Instant;

import io.github.pixeldev.urlshortener.domain.model.UserModel;

public class UserCacheDto {
  private String id;
  private String fullName;
  private Instant createdAt;

  public UserCacheDto() {}

  public static UserCacheDto fromModel(final UserModel model) {
    final UserCacheDto dto = new UserCacheDto();
    dto.setId(model.getId());
    dto.setFullName(model.getFullName());
    dto.setCreatedAt(model.getCreatedAt());
    return dto;
  }

  public UserModel toDomain() {
    return new UserModel(this.id, this.createdAt, this.fullName);
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(final Instant createdAt) {
    this.createdAt = createdAt;
  }
}
