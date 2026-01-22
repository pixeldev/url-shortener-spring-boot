package io.github.pixeldev.urlshortener.application.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record GetUrlShortenedResponse(@NotBlank @URL String originalUrl) {}
