package kr.co.leteatgo.auth.application.jwt;

import static kr.co.leteatgo.common.auth.jwt.JwtParser.*;

import java.time.Duration;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.leteatgo.common.auth.jwt.Account;
import kr.co.leteatgo.common.auth.jwt.JwtSet;
import kr.co.leteatgo.common.auth.jwt.JwtToken;
import kr.co.leteatgo.common.auth.jwt.TokenType;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;

public class JwtGenerator {

	private static final String ISSUER = "leteatgo";
	private static final Duration AT_EXP_MIN = Duration.ofMinutes(30);
	private static final Duration AT_EXP_MAX = Duration.ofMinutes(120);
	private static final Duration RT_EXP_MIN = Duration.ofDays(3);
	private static final Duration RT_EXP_MAX = Duration.ofDays(14);

	private final SecretKeyManager secretKeyManager;
	private Duration defaultAtExp;
	private Duration defaultRtExp;

	public JwtGenerator(SecretKeyManager secretKeyManager, Duration accessTokenExpTime,
		Duration refreshTokenExpTime) {

		this.secretKeyManager = secretKeyManager;

		validTokenExpTime(TokenType.ACCESS, accessTokenExpTime);
		this.defaultAtExp = accessTokenExpTime;

		validTokenExpTime(TokenType.REFRESH, refreshTokenExpTime);
		this.defaultRtExp = refreshTokenExpTime;
	}

	private static void validTokenExpTime(TokenType tokenType, Duration expTime) {
		Duration min = tokenType == TokenType.ACCESS ? AT_EXP_MIN : RT_EXP_MIN;
		Duration max = tokenType == TokenType.ACCESS ? AT_EXP_MAX : RT_EXP_MAX;
		if (expTime.compareTo(min) < 0 || expTime.compareTo(max) > 0) {
			throw new LegException(ErrorCode.BAD_REQUEST,
				String.format("%s 토큰의 만료 기간은 최소 %s 최대 %s 입니다.", tokenType, min, max));
		}
	}

	private static Claims createDefaultClaims(Account account, Duration expirationTime) {
		Date now = new Date();
		Date exp = new Date(now.getTime() + expirationTime.toMillis());
		return Jwts.claims()
			.setIssuer(ISSUER)
			.setIssuedAt(now)
			.setExpiration(exp)
			.setSubject(account.id().toString());
	}

	public void changeDefaultExpirationTime(TokenType tokenType, Duration expirationTime) {
		if (tokenType == TokenType.ACCESS) {
			this.defaultAtExp = expirationTime;
		} else {
			this.defaultRtExp = expirationTime;
		}
	}

	public JwtSet generateJwtSet(Account account) {
		return this.generateJwtSet(account, defaultAtExp, defaultRtExp);
	}

	public JwtSet generateJwtSet(Account account, Duration atExp, Duration rtExp) {
		JwtToken accessToken = generateJwtToken(TokenType.ACCESS, account, atExp);
		JwtToken refreshToken = generateJwtToken(TokenType.REFRESH, account, rtExp);
		return new JwtSet(accessToken, refreshToken);
	}

	public JwtToken generateJwtToken(TokenType tokenType, Account account) {
		return this.generateJwtToken(tokenType, account,
			tokenType == TokenType.ACCESS ? defaultAtExp : defaultRtExp);
	}

	public JwtToken generateJwtToken(TokenType tokenType, Account account, Duration expirationTime) {
		String token = Jwts.builder()
			.setClaims(createClaims(tokenType, account, expirationTime))
			.signWith(SignatureAlgorithm.HS256, secretKeyManager.getSecretKey())
			.compact();
		return JwtToken.valueOf(token);
	}

	private Claims createClaims(TokenType tokenType, Account account, Duration expirationTime) {
		if (tokenType == TokenType.ACCESS) {
			return createAtClaims(account, expirationTime);
		}
		return createRtClaims(account, expirationTime);
	}

	private Claims createAtClaims(Account account, Duration expirationTime) {
		Claims claims = createDefaultClaims(account, expirationTime);
		claims.put(CLAIM_ACCOUNT_TYPE, account.accountType());
		claims.put(CLAIM_ROLES, account.roles());
		claims.put(CLAIM_TOKEN_TYPE, TokenType.ACCESS);
		claims.put("accountType", account.accountType());
		claims.put("roles", account.roles());
		claims.put("tokenType", true);
		return claims;
	}

	private Claims createRtClaims(Account account, Duration expirationTime) {
		Claims claims = createDefaultClaims(account, expirationTime);
		claims.put(CLAIM_ACCOUNT_TYPE, account.accountType());
		claims.put(CLAIM_ROLES, account.roles());
		claims.put(CLAIM_TOKEN_TYPE, TokenType.REFRESH);
		claims.put("accountType", account.accountType());
		claims.put("roles", account.roles());
		claims.put("tokenType", false);
		return claims;
	}
}
