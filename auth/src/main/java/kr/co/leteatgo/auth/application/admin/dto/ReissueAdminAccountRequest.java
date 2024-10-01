package kr.co.leteatgo.auth.application.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record ReissueAdminAccountRequest(@NotBlank String refreshToken) {

}
