package io.github.pixeldev.urlshortener.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

public record GetUrlShortenedRequest(
    @NotBlank(message = "ID cannot be blank") @NotNull(message = "ID cannot be null") String id,
    @Nullable @Positive(message = "User ID must be positive") Long userId) {
}
