package io.github.pixeldev.urlshortener.domain.exception;

public class ExpiredUrlException extends DomainException {
  public ExpiredUrlException(String message) {
    super("URL_EXPIRED", message);
  }
}
