package io.github.pixeldev.urlshortener.application.usecase;

import java.time.LocalDateTime;

import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;
import io.github.pixeldev.urlshortener.application.port.adapter.RandomIdPort;
import io.github.pixeldev.urlshortener.application.port.adapter.UrlShortenedPort;
import io.github.pixeldev.urlshortener.application.port.adapter.UserPort;
import io.github.pixeldev.urlshortener.application.port.usecase.ShortUrlUseCasePort;
import io.github.pixeldev.urlshortener.domain.model.UrlShortenedModel;
import io.github.pixeldev.urlshortener.domain.model.UserModel;

public class ShortUrlUseCase implements ShortUrlUseCasePort {
  private final RandomIdPort randomIdPort;
  private final UrlShortenedPort urlShortenedPort;
  private final UserPort userPort;

  public ShortUrlUseCase(
      final RandomIdPort randomIdPort,
      final UrlShortenedPort urlShortenedPort,
      final UserPort userPort) {
    this.randomIdPort = randomIdPort;
    this.urlShortenedPort = urlShortenedPort;
    this.userPort = userPort;
  }

  @Override
  public ShortUrlResponse execute(final ShortUrlRequest request) {
    final String randomId = this.randomIdPort.generate();
    final LocalDateTime expirationDate =
        request.expirationTimeInMinutes() == null
            ? null
            : LocalDateTime.now().plusMinutes(request.expirationTimeInMinutes());
    final UserModel userModel = this.userPort.findByIdSafe(request.userId());
    final UrlShortenedModel entity =
        new UrlShortenedModel(
            randomId,
            LocalDateTime.now(),
            userModel,
            request.isPrivate(),
            request.url(),
            expirationDate);
    final UrlShortenedModel savedModel = this.urlShortenedPort.save(entity);
    return new ShortUrlResponse(
        savedModel.getId(), savedModel.getOriginalUrl(), savedModel.getExpirationDate());
  }
}
