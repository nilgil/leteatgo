package kr.co.leteatgo.auth.application.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RegisterStoreAccountRequest(
    @NotNull UUID storeId,
    @NotBlank String loginId,
    @NotBlank String loginPwd
) {

}
