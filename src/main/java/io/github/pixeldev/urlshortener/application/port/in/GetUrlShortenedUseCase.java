package io.github.pixeldev.urlshortener.application.port.in;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;

public interface GetUrlShortenedUseCase {
  GetUrlShortenedResponse execute(final GetUrlShortenedRequest request);
}
