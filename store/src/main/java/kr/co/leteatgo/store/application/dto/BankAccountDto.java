package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotBlank;

public record BankAccountDto(
    @NotBlank String bankName,
    @NotBlank String accountHolder,
    @NotBlank String accountNumber
) {

}
