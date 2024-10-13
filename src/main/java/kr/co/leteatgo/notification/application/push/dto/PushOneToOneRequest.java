package kr.co.leteatgo.notification.application.push.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PushOneToOneRequest(
	@NotBlank String deviceToken,
	@NotNull PushInfo pushInfo
) {

}
