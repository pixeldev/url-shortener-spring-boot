package io.github.pixeldev.urlshortener.infrastructure.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.pixeldev.urlshortener.domain.exception.AccessDeniedException;
import io.github.pixeldev.urlshortener.domain.exception.ExpiredUrlException;
import io.github.pixeldev.urlshortener.domain.exception.ModelNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ExpiredUrlException.class)
  public ResponseEntity<ErrorResponse> handleExpired(ExpiredUrlException ex) {
    return ResponseEntity.status(HttpStatus.GONE)
        .body(new ErrorResponse("URL_EXPIRED", ex.getMessage()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponse("FORBIDDEN_ACCESS", ex.getMessage()));
  }

  @ExceptionHandler(ModelNotFoundException.class)
  public ResponseEntity<Object> handleNotFound(ModelNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("MODEL_NOT_FOUND", ex.getMessage()));
  }

  public record ErrorResponse(LocalDateTime timestamp, String code, String message) {
    public ErrorResponse(final String code, final String message) {
      this(LocalDateTime.now(), code, message);
    }
  }
}
