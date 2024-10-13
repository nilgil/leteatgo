package kr.co.leteatgo.common.exception;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum ErrorCode {

	BAD_REQUEST(400, "400000", "Bad Request", true),
	INVALID_FORMAT(400, "400100", "유효하지 않은 형식입니다.", false),
	INVALID_CALCULATION(400, "400200", "계산 결과가 유효하지 않습니다.", false),

	UNAUTHORIZED(401, "401000", "Unauthorized", true),
	BAD_CREDENTIAL(401, "401100", "유효하지 않은 인증 정보입니다.", false),
	NOT_ACTIVATED_ACCOUNT(401, "401200", "활성화 되지 않은 계정입니다.", false),
	LOCKED_ACCOUNT(401, "401300", "계정이 잠긴 상태입니다.", false),

	FORBIDDEN(403, "403000", "Forbidden", true),
	NO_AUTHORITY_RESOURCE(403, "403000", "자원에 대한 권한이 없습니다.", false),
	NO_REGISTERED_DEVICE(403, "403200", "등록되지 않은 기기입니다.", false),
	BLOCK_ACCOUNT(403, "403300", "사용할 수 없는 계정입니다.", false),
	EXPIRED_TOKEN(403, "403300", "유효기간이 만료된 토큰입니다", false),
	REFERENCE_RESOURCE(403, "403700", "해당 자원을 참조하는 자원이 있습니다.", false),

	NOT_FOUND(404, "404300", "Not Found", true),
	NOT_FOUND_RESOURCE(404, "404300", "자원을 찾을 수 없습니다.", false),

	CONFLICT(409, "409000", "Conflict", true),
	DUPLICATED_RESOURCE(409, "409100", "중복되는 데이터가 존재합니다.", false),

	INTERNAL_SERVER_ERROR(500, "500000", "Internal Server Error", true),
	UNKNOWN_SERVER_ERROR(500, "500999", "시스템 오류가 발생했습니다.", false);

	private final int status;
	private final String subCode;
	private final String message;
	private final Boolean official;

	ErrorCode(int status, String subCode, String message, Boolean official) {
		this.status = status;
		this.subCode = subCode;
		this.message = message;
		this.official = official;
	}

	public static ErrorCode findByStatus(int status) {
		return Arrays.stream(ErrorCode.values())
			.filter(errorCode -> errorCode.status == status)
			.filter(errorCode -> errorCode.official)
			.findFirst()
			.orElse(UNKNOWN_SERVER_ERROR);
	}

	public static ErrorCode findBySubCode(String subCode) {
		return Arrays.stream(ErrorCode.values())
			.filter(errorCode -> errorCode.subCode.equals(subCode))
			.findFirst()
			.orElse(UNKNOWN_SERVER_ERROR);
	}

}
