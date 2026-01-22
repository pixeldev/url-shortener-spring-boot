package io.github.pixeldev.urlshortener.infrastructure.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
  @Bean
  public Clock clock() {
    return Clock.systemUTC();
  }
}
