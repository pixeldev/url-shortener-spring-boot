package io.github.pixeldev.urlshortener.application.port.usecase;

import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;

public interface ShortUrlUseCasePort {
  ShortUrlResponse execute(final ShortUrlRequest request);
}
