package kr.co.leteatgo.common.security.filter;

import static kr.co.leteatgo.common.auth.ApiKey.*;
import static kr.co.leteatgo.common.auth.LegRoleHierarchy.*;
import static kr.co.leteatgo.common.auth.jwt.JwtToken.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.leteatgo.common.auth.jwt.AccountType;
import kr.co.leteatgo.common.auth.jwt.JwtParser;
import kr.co.leteatgo.common.auth.jwt.JwtToken;
import kr.co.leteatgo.common.auth.jwt.TokenInfo;
import kr.co.leteatgo.common.auth.jwt.TokenType;

public class JwtAuthFilter extends OncePerRequestFilter {

	private static String getAuthorizationHeader(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		return httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
	}

	private static boolean isAnonymous(String authorization) {
		return !StringUtils.hasText(authorization) || !allowedAuthType(authorization);
	}

	private static boolean allowedAuthType(String authorization) {
		return authorization.startsWith(BEARER_PREFIX) || authorization.startsWith(API_KEY_PREFIX);
	}

	private static Authentication createAuthentication(TokenInfo tokenInfo) {
		UUID principal = tokenInfo.id();
		AccountType credentials = tokenInfo.accountType();
		List<GrantedAuthority> authorities = tokenInfo.roles().stream()
			.map(role -> (GrantedAuthority)() -> AUTHORITY_PREFIX + role)
			.toList();
		return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
	}

	private static void resetSecurityContext(Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	@Override
	protected void doFilterInternal(
		@Nonnull HttpServletRequest request,
		@Nonnull HttpServletResponse response,
		@Nonnull FilterChain filterChain
	) throws ServletException, IOException {

		String authorization = getAuthorizationHeader(request);
		if (isAnonymous(authorization)) {
			filterChain.doFilter(request, response); // as anonymous
			return;
		}

		if (authorization.startsWith(API_KEY_PREFIX)) {
			authorization = request.getAttribute(HttpHeaders.AUTHORIZATION).toString();
		}

		JwtToken jwtToken = JwtToken.valueOf(authorization);
		TokenInfo tokenInfo = JwtParser.parse(jwtToken);
		if (tokenInfo.tokenType() == TokenType.REFRESH) {
			throw new IllegalArgumentException("cannot request with refresh token");
		}

		resetSecurityContext(createAuthentication(tokenInfo));

		filterChain.doFilter(request, response);
	}
}
