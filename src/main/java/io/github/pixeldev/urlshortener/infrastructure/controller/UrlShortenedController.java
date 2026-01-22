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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/url-shortened")
@Tag(name = "URL Shortener", description = "Endpoints for creating and retrieving shortened URLs")
public class UrlShortenedController {
  private final GetUrlShortenedUseCasePort getUrlShortenedUseCasePort;
  private final ShortUrlUseCasePort shortUrlUseCasePort;

  public UrlShortenedController(
      GetUrlShortenedUseCasePort getUrlShortenedUseCasePort,
      ShortUrlUseCasePort shortUrlUseCasePort) {
    this.getUrlShortenedUseCasePort = getUrlShortenedUseCasePort;
    this.shortUrlUseCasePort = shortUrlUseCasePort;
  }

  @Operation(
      summary = "Get original URL",
      description =
          "Retrieves the original URL for a given shortened ID. Requires ownership check if private.",
      responses = {
        @ApiResponse(responseCode = "200", description = "URL found and returned"),
        @ApiResponse(
            responseCode = "404",
            description = "Shortened URL not found",
            content =
                @Content(
                    schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        @ApiResponse(
            responseCode = "403",
            description = "URL is private and access is denied",
            content =
                @Content(
                    schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        @ApiResponse(
            responseCode = "410",
            description = "URL has expired",
            content =
                @Content(
                    schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class)))
      })
  @GetMapping("/{id}")
  public ResponseEntity<GetUrlShortenedResponse> getUrlShortened(
      @Parameter(description = "The unique 6-character short ID", example = "aB3d9Z") @PathVariable
          String id,
      @Parameter(description = "User ID attempting access", example = "1")
          @RequestParam(required = false)
          @Nullable
          Long userId) {
    return ResponseEntity.ok(
        this.getUrlShortenedUseCasePort.execute(new GetUrlShortenedRequest(id, userId)));
  }

  @Operation(
      summary = "Create shortened URL",
      responses = {
        @ApiResponse(responseCode = "200", description = "URL shortened successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content =
                @Content(
                    schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class)))
      })
  @PostMapping
  public ResponseEntity<ShortUrlResponse> createShortUrl(@RequestBody ShortUrlRequest request) {
    return ResponseEntity.ok(this.shortUrlUseCasePort.execute(request));
  }
}
