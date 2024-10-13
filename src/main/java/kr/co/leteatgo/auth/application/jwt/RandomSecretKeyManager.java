package kr.co.leteatgo.auth.application.jwt;

import java.security.Key;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Profile("local")
@Service
public class RandomSecretKeyManager implements SecretKeyManager {

	private Key secretKey;

	@PostConstruct
	void init() {
		secretKey = KeyUtil.createSecretKey();
	}

	@Override
	public Key getSecretKey() {
		return secretKey;
	}

	@Override
	public void changeSecretKey() {
		secretKey = KeyUtil.createSecretKey();
	}
}
