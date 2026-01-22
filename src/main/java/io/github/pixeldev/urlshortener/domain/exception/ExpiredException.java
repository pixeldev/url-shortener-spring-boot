package io.github.pixeldev.urlshortener.domain.exception;

public class ExpiredException extends DomainException {
  public ExpiredException(final String message) {
    super("EXPIRED", message);
  }
}
