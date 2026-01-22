package io.github.pixeldev.urlshortener.infrastructure.controller.docs;

import java.lang.annotation.*;

import io.github.pixeldev.urlshortener.infrastructure.controller.GlobalExceptionHandler;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
    responseCode = "404",
    description = "Resource not found",
    content =
        @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class)))
public @interface ApiNotFoundResponse {}
