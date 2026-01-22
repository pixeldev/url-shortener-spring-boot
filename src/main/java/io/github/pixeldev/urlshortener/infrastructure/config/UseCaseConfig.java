package io.github.pixeldev.urlshortener.infrastructure.config;

import java.time.Clock;

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
      final Clock clock,
      final RandomIdPort randomIdPort,
      final UrlShortenedPort urlShortenedPort,
      final UserPort userPort) {
    return new ShortUrlUseCase(clock, randomIdPort, urlShortenedPort, userPort);
  }

  @Bean
  public GetUrlShortenedUseCase getUrlShortenedUseCase(
      final Clock clock, final UrlShortenedPort urlShortenedPort) {
    return new GetUrlShortenedUseCase(clock, urlShortenedPort);
  }
}
