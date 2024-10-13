package kr.co.leteatgo.auth.domain.apikey;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import kr.co.leteatgo.common.auth.ApiKey;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;

public class ApiKeyUtils {

	private static final int AK_MIN_LENGTH = 32;
	private static final int AK_MAX_LENGTH = 32;

	public static ApiKey generate(UUID id) {
		return ApiKey.valueOf(encodeBase64(genCombineKey(id, genRandomKey())));
	}

	public static UUID parse(ApiKey apiKey) {
		try {
			String combineKey = decodeBase64(apiKey);
			return extractUuid(combineKey);
		} catch (Exception e) {
			throw new LegException(ErrorCode.BAD_CREDENTIAL);
		}
	}

	private static String genRandomKey() {
		return RandomStringUtils.randomAlphanumeric(AK_MIN_LENGTH, AK_MAX_LENGTH);
	}

	private static String genCombineKey(UUID id, String randomKey) {
		return id + ":" + randomKey;
	}

	private static String encodeBase64(String combineKey) {
		return Base64.encodeBase64String(combineKey.getBytes());
	}

	private static String decodeBase64(ApiKey apiKey) {
		byte[] decode = Base64.decodeBase64(apiKey.value());
		return new String(decode);
	}

	private static UUID extractUuid(String combineKey) {
		return UUID.fromString(combineKey.split(":")[0]);
	}
}
