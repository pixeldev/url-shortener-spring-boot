package io.github.pixeldev.urlshortener.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedRequest;
import io.github.pixeldev.urlshortener.application.dto.GetUrlShortenedResponse;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlRequest;
import io.github.pixeldev.urlshortener.application.dto.ShortUrlResponse;
import io.github.pixeldev.urlshortener.application.port.usecase.GetUrlShortenedUseCasePort;
import io.github.pixeldev.urlshortener.application.port.usecase.ShortUrlUseCasePort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/url-shortened")
public class UrlShortenedController {
  private final GetUrlShortenedUseCasePort getUrlShortenedUseCasePort;
  private final ShortUrlUseCasePort shortUrlUseCasePort;

  public UrlShortenedController(
      GetUrlShortenedUseCasePort getUrlShortenedUseCasePort,
      ShortUrlUseCasePort shortUrlUseCasePort) {
    this.getUrlShortenedUseCasePort = getUrlShortenedUseCasePort;
    this.shortUrlUseCasePort = shortUrlUseCasePort;
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetUrlShortenedResponse> getUrlShortened(
      @PathVariable String id, @RequestParam long userId) {
    return ResponseEntity.ok(
        this.getUrlShortenedUseCasePort.execute(new GetUrlShortenedRequest(id, userId)));
  }

  @PostMapping
  public ResponseEntity<ShortUrlResponse> createShortUrl(
      @RequestBody @Valid ShortUrlRequest request) {
    return ResponseEntity.ok(this.shortUrlUseCasePort.execute(request));
  }
}
