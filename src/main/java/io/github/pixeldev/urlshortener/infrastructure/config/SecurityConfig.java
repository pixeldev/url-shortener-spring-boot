package io.github.pixeldev.urlshortener.infrastructure.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.pixeldev.urlshortener.infrastructure.adapter.in.rest.handler.GlobalExceptionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
  private String jwkSetUri;

  @Bean
  public SecurityFilterChain securityFilterChain(
      final HttpSecurity http, final ObjectMapper objectMapper) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**")
                    .permitAll()
                    .requestMatchers(
                        org.springframework.http.HttpMethod.GET, "/v1/url-shortened/**")
                    .permitAll()
                    .requestMatchers("/v1/url-shortened/**")
                    .authenticated()
                    .anyRequest()
                    .denyAll())
        .oauth2ResourceServer(
            oauth2 ->
                oauth2
                    .jwt(jwt -> jwt.decoder(jwtDecoder()))
                    .authenticationEntryPoint(
                        (request, response, authException) -> {
                          response.setStatus(401);
                          response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                          GlobalExceptionHandler.ErrorResponse error =
                              new GlobalExceptionHandler.ErrorResponse(
                                  "UNAUTHORIZED", "Invalid or missing token");
                          response.getWriter().write(objectMapper.writeValueAsString(error));
                        }));

    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    final NimbusJwtDecoder jwtDecoder =
        NimbusJwtDecoder.withJwkSetUri(jwkSetUri).jwsAlgorithm(SignatureAlgorithm.ES256).build();

    final OAuth2TokenValidator<Jwt> audienceValidator =
        new JwtClaimValidator<>(
            JwtClaimNames.AUD,
            aud -> {
              if (aud instanceof String s) {
                return s.contains("authenticated");
              }
              if (aud instanceof List<?> list) {
                return list.contains("authenticated");
              }
              return false;
            });

    final OAuth2TokenValidator<Jwt> withTimestamp = new JwtTimestampValidator();
    final OAuth2TokenValidator<Jwt> validator =
        new DelegatingOAuth2TokenValidator<>(withTimestamp, audienceValidator);

    jwtDecoder.setJwtValidator(validator);

    return jwtDecoder;
  }
}
