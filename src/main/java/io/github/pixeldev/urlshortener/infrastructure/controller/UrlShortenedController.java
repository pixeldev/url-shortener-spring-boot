package io.github.pixeldev.urlshortener.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;
import io.github.pixeldev.urlshortener.application.port.usecase.GetUrlShortenedUseCasePort;
import io.github.pixeldev.urlshortener.application.port.usecase.ShortUrlUseCasePort;
import io.github.pixeldev.urlshortener.infrastructure.controller.docs.ApiAccessDeniedResponse;
import io.github.pixeldev.urlshortener.infrastructure.controller.docs.ApiBadRequestResponse;
import io.github.pixeldev.urlshortener.infrastructure.controller.docs.ApiExpiredResponse;
import io.github.pixeldev.urlshortener.infrastructure.controller.docs.ApiNotFoundResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/url-shortened")
@Tag(name = "URL Shortener", description = "Endpoints for creating and retrieving shortened URLs")
public class UrlShortenedController {
  private final GetUrlShortenedUseCasePort getUrlShortenedUseCasePort;
  private final ShortUrlUseCasePort shortUrlUseCasePort;

  public UrlShortenedController(
      final GetUrlShortenedUseCasePort getUrlShortenedUseCasePort,
      final ShortUrlUseCasePort shortUrlUseCasePort) {
    this.getUrlShortenedUseCasePort = getUrlShortenedUseCasePort;
    this.shortUrlUseCasePort = shortUrlUseCasePort;
  }

  @Operation(
      summary = "Get original URL",
      description = "Retrieves the original URL for a given ID.")
  @ApiNotFoundResponse
  @ApiBadRequestResponse
  @ApiAccessDeniedResponse
  @ApiExpiredResponse
  @ApiResponse(responseCode = "200", description = "URL found")
  @GetMapping("/{id}")
  public ResponseEntity<GetUrlShortenedResponse> getUrlShortened(
      @PathVariable String id, @RequestParam(required = false) @Nullable Long userId) {
    return ResponseEntity.ok(
        this.getUrlShortenedUseCasePort.execute(new GetUrlShortenedRequest(id, userId)));
  }

  @Operation(summary = "Create shortened URL")
  @ApiBadRequestResponse
  @ApiResponse(responseCode = "200", description = "URL shortened successfully")
  @PostMapping
  public ResponseEntity<ShortUrlResponse> createShortUrl(@RequestBody ShortUrlRequest request) {
    return ResponseEntity.ok(this.shortUrlUseCasePort.execute(request));
  }
}
