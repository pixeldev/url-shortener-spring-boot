package io.github.pixeldev.urlshortener.domain.model;

import java.time.Instant;

public class UserModel {
  private final String id;
  private final Instant createdAt;
  private String fullName;

  public UserModel(final String id, final Instant createdAt, final String fullName) {
    this.id = id;
    this.createdAt = createdAt;
    this.fullName = fullName;
  }

  public String getId() {
    return id;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(final String fullName) {
    this.fullName = fullName;
  }
}
