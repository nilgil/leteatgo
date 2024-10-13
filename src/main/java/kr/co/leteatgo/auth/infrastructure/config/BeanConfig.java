package kr.co.leteatgo.auth.infrastructure.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

import kr.co.leteatgo.auth.application.jwt.JwtGenerator;
import kr.co.leteatgo.auth.application.jwt.SecretKeyManager;
import lombok.RequiredArgsConstructor;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class BeanConfig {

	private static final int CACHE_EXP_MINUTES = 25;

	private final SecretKeyManager secretKeyManager;

	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder()
			.expireAfterWrite(CACHE_EXP_MINUTES, TimeUnit.MINUTES)
			.maximumSize(1000));
		return cacheManager;
	}

	@Bean
	public JwtGenerator jwtGenerator(
		@Value("${jwt.default-access-token-exp-time}") Duration accessTokenExpTime,
		@Value("${jwt.default-refresh-token-exp-time}") Duration refreshTokenExpTime
	) {
		return new JwtGenerator(secretKeyManager, accessTokenExpTime, refreshTokenExpTime);
	}
}
