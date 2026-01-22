package io.github.pixeldev.urlshortener.application.usecase;

import java.time.Clock;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;
import io.github.pixeldev.urlshortener.application.port.adapter.UrlShortenedPort;
import io.github.pixeldev.urlshortener.application.port.usecase.GetUrlShortenedUseCasePort;
import io.github.pixeldev.urlshortener.domain.exception.AccessDeniedException;
import io.github.pixeldev.urlshortener.domain.exception.ExpiredUrlException;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;

public class GetUrlShortenedUseCase implements GetUrlShortenedUseCasePort {
  private final Clock clock;
  private final UrlShortenedPort urlShortenedPort;

  public GetUrlShortenedUseCase(final Clock clock, final UrlShortenedPort urlShortenedPort) {
    this.clock = clock;
    this.urlShortenedPort = urlShortenedPort;
  }

  @Override
  public GetUrlShortenedResponse execute(final GetUrlShortenedRequest request) {
    final UrlShortenedModel urlShortenedModel =
        this.urlShortenedPort.findByIdWithUserSafe(request.id());
    if (urlShortenedModel.isExpired(this.clock)) {
      throw new ExpiredUrlException(request.id());
    }
    if (!urlShortenedModel.isOwner(request.userId())) {
      throw new AccessDeniedException("User does not have access to this URL shortened");
    }
    return new GetUrlShortenedResponse(urlShortenedModel.getOriginalUrl());
  }
}
