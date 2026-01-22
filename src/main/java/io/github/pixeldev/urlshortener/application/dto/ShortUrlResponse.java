package io.github.pixeldev.urlshortener.application.dto;

import java.time.LocalDateTime;

public record ShortUrlResponse(String shortUrl, String originalUrl, LocalDateTime expirationDate) {}
