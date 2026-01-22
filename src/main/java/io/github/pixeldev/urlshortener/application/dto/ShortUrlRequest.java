package io.github.pixeldev.urlshortener.application.dto;

import static io.github.pixeldev.urlshortener.domain.validation.Ensure.*;

import org.springframework.lang.Nullable;

public record ShortUrlRequest(
    String url,
    boolean isPrivate,
    @Nullable String userId,
    @Nullable Long expirationTimeInMinutes) {

  public ShortUrlRequest {
    notBlank(url, "URL cannot be blank or null");
    matches(url, URL_PATTERN, "Invalid URL format");
    positive(expirationTimeInMinutes, "Expiration time must be positive");
  }
}
