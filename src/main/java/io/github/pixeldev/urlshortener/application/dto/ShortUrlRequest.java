package io.github.pixeldev.urlshortener.application.dto;

import org.springframework.lang.Nullable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ShortUrlRequest(
    @NotNull(message = "URL cannot be null")
        @NotBlank(message = "URL cannot be blank")
        @Pattern(regexp = "^(http|https)://.*", message = "Invalid URL format")
        String url,
    boolean isPrivate,
    @Nullable @Positive(message = "User ID must be positive") Long userId,
    @Nullable @Positive(message = "Expiration time must be positive")
        Long expirationTimeInMinutes) {}
