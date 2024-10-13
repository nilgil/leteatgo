package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotNull;

public record TermAgreementsDto(
	@NotNull Boolean serviceUse,
	@NotNull Boolean collectPersonalInfo,
	@NotNull Boolean collectPersonalInfoForEvent,
	@NotNull Boolean receiveAdSms,
	@NotNull Boolean receiveAdEmail,
	@NotNull Boolean receiveAdCall
) {

}
