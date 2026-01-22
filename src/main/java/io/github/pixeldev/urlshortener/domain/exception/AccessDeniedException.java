package io.github.pixeldev.urlshortener.domain.exception;

public class AccessDeniedException extends DomainException {
  public AccessDeniedException(final String message) {
    super("ACCESS_DENIED", message);
  }
}
