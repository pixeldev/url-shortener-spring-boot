package io.github.pixeldev.urlshortener.domain.exception;

public class BadRequestException extends DomainException {
  public BadRequestException(final String message) {
    super("BAD_REQUEST", message);
  }
}
