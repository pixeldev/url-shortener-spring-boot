package io.github.pixeldev.urlshortener.application.usecase;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;
import io.github.pixeldev.urlshortener.application.port.adapter.UrlShortenedPort;
import io.github.pixeldev.urlshortener.application.port.usecase.GetUrlShortenedUseCasePort;
import io.github.pixeldev.urlshortener.domain.exception.AccessDeniedException;
import io.github.pixeldev.urlshortener.domain.exception.ExpiredUrlException;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;

public class GetUrlShortenedUseCase implements GetUrlShortenedUseCasePort {
  private final UrlShortenedPort urlShortenedPort;

  public GetUrlShortenedUseCase(final UrlShortenedPort urlShortenedPort) {
    this.urlShortenedPort = urlShortenedPort;
  }

  @Override
  public GetUrlShortenedResponse execute(final GetUrlShortenedRequest request) {
    final UrlShortenedModel urlShortenedModel =
        this.urlShortenedPort.findByIdWithUserSafe(request.id());
    if (urlShortenedModel.isExpired()) {
      throw new ExpiredUrlException(request.id());
    }
    if (!urlShortenedModel.isOwner(request.userId())) {
      throw new AccessDeniedException("User does not have access to this URL shortened");
    }
    return new GetUrlShortenedResponse(urlShortenedModel.getOriginalUrl());
  }
}
