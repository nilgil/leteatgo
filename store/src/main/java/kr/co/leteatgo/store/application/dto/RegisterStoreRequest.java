package kr.co.leteatgo.store.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.leteatgo.store.domain.store.entity.StoreAgent;

public record RegisterStoreRequest(
    @NotBlank String name,
    @NotBlank String phoneNumber,
    StoreAgent agent,
    @Valid @NotNull LicenseDto license,
    @Valid @NotNull CredentialDto credential,
    @Valid @NotNull ContactDto contact,
    @Valid @NotNull TermAgreementsDto termAgreements,
    @Valid @NotNull LocationDto location,
    @Valid @NotNull BankAccountDto bankAccount
) {

}
