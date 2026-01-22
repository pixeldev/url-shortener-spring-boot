package io.github.pixeldev.urlshortener.infrastructure.controller.docs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.pixeldev.urlshortener.infrastructure.controller.GlobalExceptionHandler;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
    responseCode = "403",
    description = "Access denied",
    content =
        @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class)))
public @interface ApiAccessDeniedResponse {}
