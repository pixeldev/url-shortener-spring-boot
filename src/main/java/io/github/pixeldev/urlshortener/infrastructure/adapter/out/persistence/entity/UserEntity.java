package io.github.pixeldev.urlshortener.infrastructure.adapter.out.persistence.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  public UserEntity(final String id, final String fullName, final Instant createdAt) {
    this.id = id;
    this.fullName = fullName;
    this.createdAt = createdAt;
  }

  public UserEntity() {}

  public String getId() {
    return id;
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
}
