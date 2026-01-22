package io.github.pixeldev.urlshortener.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pixeldev.urlshortener.application.port.adapter.RandomIdPort;
import io.github.pixeldev.urlshortener.application.port.adapter.UrlShortenedPort;
import io.github.pixeldev.urlshortener.application.port.adapter.UserPort;
import io.github.pixeldev.urlshortener.application.usecase.GetUrlShortenedUseCase;
import io.github.pixeldev.urlshortener.application.usecase.ShortUrlUseCase;

@Configuration
public class UseCaseConfig {
  @Bean
  public ShortUrlUseCase shortUrlUseCase(
      final RandomIdPort randomIdPort,
      final UrlShortenedPort urlShortenedPort,
      final UserPort userPort) {
    return new ShortUrlUseCase(randomIdPort, urlShortenedPort, userPort);
  }

  @Bean
  public GetUrlShortenedUseCase getUrlShortenedUseCase(final UrlShortenedPort urlShortenedPort) {
    return new GetUrlShortenedUseCase(urlShortenedPort);
  }
}
