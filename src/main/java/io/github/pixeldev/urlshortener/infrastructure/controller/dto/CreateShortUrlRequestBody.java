package io.github.pixeldev.urlshortener.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Request to create a shortened URL")
public record CreateShortUrlRequestBody(
    @Schema(description = "Original long URL to shorten", example = "https://github.com/pixeldev")
        @NotBlank
        @NotNull
        @Pattern(regexp = "^(http|https)://.*")
        String url,
    @Schema(description = "If true, only the owner can access stats", example = "false")
        boolean isPrivate,
    @Schema(description = "Expiration time in minutes from now", example = "1440")
        @PositiveOrZero
        @Nullable
        Long expirationTimeInMinutes) {}
