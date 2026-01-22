package io.github.pixeldev.urlshortener.application.dto;

import java.time.Instant;

public record ShortUrlResponse(String shortUrl, String originalUrl, Instant expirationDate) {}
