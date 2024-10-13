package kr.co.leteatgo.notification.application.push.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PushOneToManyRequest(
	@NotEmpty List<String> deviceTokens,
	@NotNull PushInfo pushInfo
) {

}
