package kr.co.leteatgo.notification.application.sms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record SendSmsRequest(
    @NotEmpty List<String> phoneNumbers,
    @NotBlank String content
) {

}
