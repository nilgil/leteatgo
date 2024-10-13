package kr.co.leteatgo.common.security.filter;

import static kr.co.leteatgo.common.auth.ApiKey.*;
import static kr.co.leteatgo.common.auth.jwt.JwtToken.*;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.leteatgo.auth.application.client.ClientAccountService;
import kr.co.leteatgo.auth.domain.apikey.ApiKeyUtils;
import kr.co.leteatgo.common.auth.ApiKey;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientAccountFilter extends OncePerRequestFilter {

	private final ClientAccountService clientAccountService;

	private static String getAuthorizationHeader(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		return httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
	}

	private static boolean isNotApiKeyAuth(String authorization) {
		return !StringUtils.hasText(authorization) || !authorization.startsWith(API_KEY_PREFIX);
	}

	private static String parseApiKey(String authorization) {
		return authorization.substring(API_KEY_PREFIX.length());
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

	private String verifyApiKey(String apiKey) {
		ApiKey key = ApiKey.valueOf(apiKey);
		UUID clientId = ApiKeyUtils.parse(key);
		return clientAccountService.verifyApiKey(clientId, key).accessToken();
	}
}
