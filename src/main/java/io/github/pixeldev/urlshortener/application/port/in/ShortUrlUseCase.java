package io.github.pixeldev.urlshortener.application.port.in;

import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;

public interface ShortUrlUseCase {
  ShortUrlResponse execute(final ShortUrlRequest request);
}
