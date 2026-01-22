package io.github.pixeldev.urlshortener.application.dto;

import static io.github.pixeldev.urlshortener.domain.validation.Ensure.*;

import org.springframework.lang.Nullable;

public record GetUrlShortenedRequest(String id, @Nullable Long userId) {
  public GetUrlShortenedRequest {
    notBlank(id, "ID cannot be blank");
    positive(userId, "User ID must be positive");
  }
}
