package kr.co.leteatgo.notification.application.email;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendEmailRequest(
	@NotNull List<@Email String> emails,
	@NotBlank String subject,
	String message
) {

}
