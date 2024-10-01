package kr.co.leteatgo.auth.application.client.dto;

import jakarta.validation.constraints.NotBlank;

public record VerifyApiKeyRequest(@NotBlank String apiKey) {

}
