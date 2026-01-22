package io.github.pixeldev.urlshortener.application.dto;

import static io.github.pixeldev.urlshortener.domain.validation.Ensure.*;

import org.springframework.lang.Nullable;

public record GetUrlShortenedRequest(String id, @Nullable String userId) {
  public GetUrlShortenedRequest {
    notBlank(id, "ID cannot be blank");
  }
}
