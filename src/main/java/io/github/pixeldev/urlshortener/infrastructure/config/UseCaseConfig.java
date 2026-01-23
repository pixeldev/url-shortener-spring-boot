package io.github.pixeldev.urlshortener.infrastructure.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pixeldev.urlshortener.application.port.out.IdGeneratorPort;
import io.github.pixeldev.urlshortener.application.port.out.UrlShortenedRepository;
import io.github.pixeldev.urlshortener.application.port.out.UserRepository;
import io.github.pixeldev.urlshortener.application.service.GetUrlShortenedService;
import io.github.pixeldev.urlshortener.application.service.ShortUrlService;

@Configuration
public class UseCaseConfig {
  @Bean
  public ShortUrlService shortUrlUseCase(
      final Clock clock,
      final IdGeneratorPort idGeneratorPort,
      final UrlShortenedRepository urlShortenedRepository,
      final UserRepository userRepository) {
    return new ShortUrlService(clock, idGeneratorPort, urlShortenedRepository, userRepository);
  }

  @Bean
  public GetUrlShortenedService getUrlShortenedUseCase(
      final Clock clock, final UrlShortenedRepository urlShortenedRepository) {
    return new GetUrlShortenedService(clock, urlShortenedRepository);
  }
}
