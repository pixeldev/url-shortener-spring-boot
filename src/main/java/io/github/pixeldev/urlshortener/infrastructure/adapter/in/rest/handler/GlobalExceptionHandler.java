package io.github.pixeldev.urlshortener.infrastructure.adapter.in.rest.handler;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.pixeldev.urlshortener.domain.exception.AccessDeniedException;
import io.github.pixeldev.urlshortener.domain.exception.EnsureFailedException;
import io.github.pixeldev.urlshortener.domain.exception.ExpiredException;
import io.github.pixeldev.urlshortener.domain.exception.ModelNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ExpiredException.class)
  public ResponseEntity<ErrorResponse> handleExpired(final ExpiredException ex) {
    log.warn("Expired: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.GONE)
        .body(new ErrorResponse(ex.getCode(), ex.getMessage()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(final AccessDeniedException ex) {
    log.warn("Access denied: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponse(ex.getCode(), ex.getMessage()));
  }

  @ExceptionHandler(ModelNotFoundException.class)
  public ResponseEntity<Object> handleNotFound(final ModelNotFoundException ex) {
    log.warn("Resource not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(ex.getCode(), ex.getMessage()));
  }

  @ExceptionHandler(EnsureFailedException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(final EnsureFailedException ex) {
    log.warn("Bad request validation: {}", ex.getMessage());
    return ResponseEntity.status(400).body(new ErrorResponse(ex.getCode(), ex.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleJsonError(final HttpMessageNotReadableException ex) {
    if (ex.getRootCause() instanceof EnsureFailedException domainEx) {
      return handleBadRequest(domainEx);
    }
    log.error("Malformed JSON request: ", ex);
    return ResponseEntity.status(400)
        .body(new ErrorResponse("INVALID_JSON", "Malformed JSON request"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(final Exception ex) {
    log.error("Unhandled exception occurred: ", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred"));
  }

  public record ErrorResponse(Instant timestamp, String code, String message) {
    public ErrorResponse(final String code, final String message) {
      this(Instant.now(), code, message);
    }
  }
}
