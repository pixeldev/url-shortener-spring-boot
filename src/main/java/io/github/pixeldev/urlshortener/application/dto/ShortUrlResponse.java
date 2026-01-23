package io.github.pixeldev.urlshortener.application.dto;

import java.time.Instant;

import org.springframework.lang.Nullable;

public record ShortUrlResponse(
    String shortUrl, String originalUrl, @Nullable Instant expirationDate) {}
