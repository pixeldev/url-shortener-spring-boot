package io.github.pixeldev.urlshortener.application.service;

import java.time.Clock;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;
import io.github.pixeldev.urlshortener.application.port.in.GetUrlShortenedUseCase;
import io.github.pixeldev.urlshortener.application.port.out.UrlShortenedRepository;
import io.github.pixeldev.urlshortener.domain.exception.AccessDeniedException;
import io.github.pixeldev.urlshortener.domain.exception.ExpiredException;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;

public class GetUrlShortenedService implements GetUrlShortenedUseCase {
  private final Clock clock;
  private final UrlShortenedRepository urlShortenedRepository;

  public GetUrlShortenedService(
      final Clock clock, final UrlShortenedRepository urlShortenedRepository) {
    this.clock = clock;
    this.urlShortenedRepository = urlShortenedRepository;
  }

  @Override
  public GetUrlShortenedResponse execute(final GetUrlShortenedRequest request) {
    final UrlShortenedModel urlShortenedModel =
        this.urlShortenedRepository.findByIdWithUserSafe(request.id());
    if (urlShortenedModel.isExpired(this.clock)) {
      throw new ExpiredException(request.id());
    }
    if (!urlShortenedModel.hasAccess(request.userId())) {
      throw new AccessDeniedException("User does not have access to this URL shortened");
    }
    return new GetUrlShortenedResponse(urlShortenedModel.getOriginalUrl());
  }
}
