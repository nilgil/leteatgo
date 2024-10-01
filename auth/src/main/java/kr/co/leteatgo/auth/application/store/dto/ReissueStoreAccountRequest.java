package kr.co.leteatgo.auth.application.store.dto;

import jakarta.validation.constraints.NotBlank;

public record ReissueStoreAccountRequest(@NotBlank String refreshToken) {

}
