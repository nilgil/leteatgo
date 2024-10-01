package kr.co.leteatgo.auth.application.jwt;

import common.exception.ErrorCode;
import common.exception.LegException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class KeyUtil {

  private static final String HMAC_SHA_256 = "HmacSHA256";

  public static Key createSecretKey() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance(HMAC_SHA_256);
      return keyGen.generateKey();
    } catch (NoSuchAlgorithmException e) {
      throw new LegException(ErrorCode.INTERNAL_SERVER_ERROR, "fail to create secret key");
    }
  }

  public static String encodeBase64(Key secretKey) {
    byte[] encoded = secretKey.getEncoded();
    return Base64.encodeBase64String(encoded);
  }

  public static Key decodeBase64(String secret) {
    byte[] decoded = Base64.decodeBase64(secret);
    return new SecretKeySpec(decoded, HMAC_SHA_256);
  }
}
