package kr.co.leteatgo.auth.application.jwt;

import java.security.Key;

public interface SecretKeyManager {

  Key getSecretKey();

  void changeSecretKey();
}
