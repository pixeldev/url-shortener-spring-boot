package io.github.pixeldev.urlshortener.application.port.usecase;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;

public interface GetUrlShortenedUseCasePort {
  GetUrlShortenedResponse execute(GetUrlShortenedRequest request);
}
