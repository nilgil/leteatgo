package kr.co.leteatgo.user.application.dto;

import jakarta.validation.constraints.NotNull;

public record ChangePushAgreementRequest(@NotNull Boolean pushAgreement) {

}
