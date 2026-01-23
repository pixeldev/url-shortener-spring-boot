package io.github.pixeldev.urlshortener.application.service;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;
import io.github.pixeldev.urlshortener.application.port.in.ShortUrlUseCase;
import io.github.pixeldev.urlshortener.application.port.out.IdGeneratorPort;
import io.github.pixeldev.urlshortener.application.port.out.UrlShortenedRepository;
import io.github.pixeldev.urlshortener.application.port.out.UserRepository;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;

public class ShortUrlService implements ShortUrlUseCase {
  private final Clock clock;
  private final IdGeneratorPort idGeneratorPort;
  private final UrlShortenedRepository urlShortenedRepository;
  private final UserRepository userRepository;

  public ShortUrlService(
      final Clock clock,
      final IdGeneratorPort idGeneratorPort,
      final UrlShortenedRepository urlShortenedRepository,
      final UserRepository userRepository) {
    this.clock = clock;
    this.idGeneratorPort = idGeneratorPort;
    this.urlShortenedRepository = urlShortenedRepository;
    this.userRepository = userRepository;
  }

  @Override
  public ShortUrlResponse execute(final ShortUrlRequest request) {
    final String randomId = this.idGeneratorPort.generate();
    final Instant now = Instant.now(this.clock);
    final Instant expirationDate =
        Optional.ofNullable(request.expirationTimeInMinutes())
            .map(minutes -> now.plus(minutes, ChronoUnit.MINUTES))
            .orElse(null);
    final UserModel userModel =
        Optional.ofNullable(request.userId()).map(this.userRepository::findByIdSafe).orElse(null);
    final UrlShortenedModel entity =
        new UrlShortenedModel(
            randomId, now, userModel, request.isPrivate(), request.url(), expirationDate, now);
    final UrlShortenedModel savedModel = this.urlShortenedRepository.save(entity);
    return new ShortUrlResponse(
        savedModel.getId(),
        savedModel.getOriginalUrl(),
        savedModel.getExpirationDate().orElse(null));
  }
}
