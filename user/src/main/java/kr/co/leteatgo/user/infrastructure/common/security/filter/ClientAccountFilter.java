package kr.co.leteatgo.user.infrastructure.common.security.filter;


import static common.auth.ApiKey.API_KEY_PREFIX;
import static common.auth.jwt.JwtToken.BEARER_PREFIX;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;

public class ClientAccountFilter extends OncePerRequestFilter {

  private final WebClient authWebClient;

  public ClientAccountFilter(WebClient authWebClient) {
    this.authWebClient = authWebClient;
  }

  @Override
  protected void doFilterInternal(@Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull FilterChain filterChain) throws ServletException, IOException {

    String authorization = getAuthorizationHeader(request);
    if (isNotApiKeyAuth(authorization)) {
      filterChain.doFilter(request, response); // pass to JwtAuthFilter
      return;
    }

    String accessToken = verifyApiKey(parseApiKey(authorization));
    request.setAttribute(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + accessToken);

    filterChain.doFilter(request, response);
  }

  private static String getAuthorizationHeader(ServletRequest request) {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    return httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
  }

  private static boolean isNotApiKeyAuth(String authorization) {
    return !StringUtils.hasText(authorization) || !authorization.startsWith(API_KEY_PREFIX);
  }

  private static String parseApiKey(String authorization) {
    return authorization.substring(API_KEY_PREFIX.length());
  }

  private String verifyApiKey(String apiKey) {
    return authWebClient.post().uri("/v1/client-accounts/verify-ak")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(Map.of("apiKey", apiKey))
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
        }).block().get("accessToken");
  }
}
