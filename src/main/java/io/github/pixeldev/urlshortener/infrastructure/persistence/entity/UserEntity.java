package io.github.pixeldev.urlshortener.infrastructure.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public UserEntity(Long id, String fullName, LocalDateTime createdAt) {
    this.id = id;
    this.fullName = fullName;
    this.createdAt = createdAt;
  }

  public UserEntity() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = java.time.LocalDateTime.now();
  }
}
