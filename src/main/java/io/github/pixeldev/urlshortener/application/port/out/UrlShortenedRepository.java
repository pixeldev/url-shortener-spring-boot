package io.github.pixeldev.urlshortener.application.port.out;

import org.springframework.lang.Nullable;

import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;

public interface UrlShortenedRepository {
  UrlShortenedModel save(final UrlShortenedModel model);

  @Nullable
  UrlShortenedModel findById(final String id);

  UrlShortenedModel findByIdWithUserSafe(final String id);
}
