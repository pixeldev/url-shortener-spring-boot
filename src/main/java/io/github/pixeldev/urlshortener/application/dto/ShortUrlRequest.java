package io.github.pixeldev.urlshortener.application.dto;

import static io.github.pixeldev.urlshortener.domain.validation.Ensure.*;

import org.springframework.lang.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create a shortened URL")
public record ShortUrlRequest(
    @Schema(description = "Original long URL to shorten", example = "https://github.com/pixeldev")
        String url,
    @Schema(description = "If true, only the owner can access stats", example = "false")
        boolean isPrivate,
    @Schema(description = "Optional user ID owner", example = "123") @Nullable Long userId,
    @Schema(description = "Expiration time in minutes from now", example = "1440") @Nullable
        Long expirationTimeInMinutes) {

  public ShortUrlRequest {
    notBlank(url, "URL cannot be blank or null");
    matches(url, URL_PATTERN, "Invalid URL format");
    positive(userId, "User ID must be positive");
    positive(expirationTimeInMinutes, "Expiration time must be positive");
  }
}
