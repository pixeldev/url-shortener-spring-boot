package io.github.pixeldev.urlshortener.domain.exception;

public class ModelNotFoundException extends DomainException {
  public ModelNotFoundException(final String message) {
    super(message);
  }

  public static ModelNotFoundException create(final String modelName, final String id) {
    return new ModelNotFoundException(String.format("%s not found with ID '%s'", modelName, id));
  }
}
