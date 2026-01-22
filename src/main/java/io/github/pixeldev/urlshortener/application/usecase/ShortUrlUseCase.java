package io.github.pixeldev.urlshortener.application.usecase;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;
import io.github.pixeldev.urlshortener.application.port.adapter.RandomIdPort;
import io.github.pixeldev.urlshortener.application.port.adapter.UrlShortenedPort;
import io.github.pixeldev.urlshortener.application.port.adapter.UserPort;
import io.github.pixeldev.urlshortener.application.port.usecase.ShortUrlUseCasePort;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;

public class ShortUrlUseCase implements ShortUrlUseCasePort {
  private final Clock clock;
  private final RandomIdPort randomIdPort;
  private final UrlShortenedPort urlShortenedPort;
  private final UserPort userPort;

  public ShortUrlUseCase(
      final Clock clock,
      final RandomIdPort randomIdPort,
      final UrlShortenedPort urlShortenedPort,
      final UserPort userPort) {
    this.clock = clock;
    this.randomIdPort = randomIdPort;
    this.urlShortenedPort = urlShortenedPort;
    this.userPort = userPort;
  }

  @Override
  public ShortUrlResponse execute(final ShortUrlRequest request) {
    final String randomId = this.randomIdPort.generate();
    final Instant now = Instant.now(this.clock);
    final Instant expirationDate =
        request.expirationTimeInMinutes() == null
            ? null
            : now.plus(request.expirationTimeInMinutes(), ChronoUnit.MINUTES);
    final UserModel userModel =
        request.userId() != null ? this.userPort.findByIdSafe(request.userId()) : null;
    final UrlShortenedModel entity =
        new UrlShortenedModel(
            randomId, now, userModel, request.isPrivate(), request.url(), expirationDate, now);
    final UrlShortenedModel savedModel = this.urlShortenedPort.save(entity);
    return new ShortUrlResponse(
        savedModel.getId(), savedModel.getOriginalUrl(), savedModel.getExpirationDate());
  }
}
