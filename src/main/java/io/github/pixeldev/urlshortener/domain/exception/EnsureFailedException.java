package io.github.pixeldev.urlshortener.domain.exception;

public class EnsureFailedException extends DomainException {
  public EnsureFailedException(final String message) {
    super("ENSURE_FAILED", message);
  }
}
