package kr.co.leteatgo.notification.application.sms.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record SendSmsRequest(
	@NotEmpty List<String> phoneNumbers,
	@NotBlank String content
) {

}
