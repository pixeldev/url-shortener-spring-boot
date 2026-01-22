package io.github.pixeldev.urlshortener.domain.validation;

import java.util.regex.Pattern;

import org.springframework.lang.Nullable;

import io.github.pixeldev.urlshortener.domain.exception.BadRequestException;

public final class Ensure {
  public static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*");

  private Ensure() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static void notBlank(final @Nullable String value, final String message) {
    if (value == null || value.isBlank()) {
      throw new BadRequestException(message);
    }
  }

  public static void positive(final @Nullable Long value, final String message) {
    if (value != null && value <= 0) {
      throw new BadRequestException(message);
    }
  }

  public static void matches(
      final @Nullable String value, final Pattern pattern, final String message) {
    if (value == null || !pattern.matcher(value).matches()) {
      throw new BadRequestException(message);
    }
  }
}
