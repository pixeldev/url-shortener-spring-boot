package io.github.pixeldev.urlshortener.infrastructure.persistence.adapter;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import io.github.pixeldev.urlshortener.application.port.adapter.RandomIdPort;

@Component
public class ShortRandomIdAdapter implements RandomIdPort {
  private static final String ALPHABET =
      "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
  private static final int ID_LENGTH = 7;
  private static final SecureRandom RANDOM = new SecureRandom();

  @Override
  public String generate() {
    StringBuilder sb = new StringBuilder(ID_LENGTH);
    for (int i = 0; i < ID_LENGTH; i++) {
      int randomIndex = RANDOM.nextInt(ALPHABET.length());
      sb.append(ALPHABET.charAt(randomIndex));
    }
    return sb.toString();
  }
}
