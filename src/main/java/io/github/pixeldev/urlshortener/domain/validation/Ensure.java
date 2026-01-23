package io.github.pixeldev.urlshortener.domain.validation;

import java.util.regex.Pattern;

import io.github.pixeldev.urlshortener.domain.exception.EnsureFailedException;

public final class Ensure {
  public static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*");

  private Ensure() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static String notBlank(final String value, final String message) {
    if (value == null || value.isBlank()) {
      throw new EnsureFailedException(message);
    }
    return value;
  }

  public static <T> T notNull(final T value, final String message) {
    if (value == null) {
      throw new EnsureFailedException(message);
    }
    return value;
  }

  public static Long positive(final Long value, final String message) {
    if (value != null && value <= 0) {
      throw new EnsureFailedException(message);
    }
    return value;
  }

  public static String matches(final String value, final Pattern pattern, final String message) {
    if (value == null || !pattern.matcher(value).matches()) {
      throw new EnsureFailedException(message);
    }
    return value;
  }
}
