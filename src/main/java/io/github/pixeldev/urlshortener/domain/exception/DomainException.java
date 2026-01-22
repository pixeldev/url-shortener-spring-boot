package io.github.pixeldev.urlshortener.domain.exception;

public abstract class DomainException extends RuntimeException {
  private final String code;

  protected DomainException(final String code, final String message) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
