package io.github.pixeldev.urlshortener.domain.exception;

public abstract class DomainException extends RuntimeException {
  protected DomainException(final String message) {
    super(message);
  }
}
