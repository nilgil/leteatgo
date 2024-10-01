package kr.co.leteatgo.user.application.dto;

public record TermAgreementsDto(
    Boolean serviceUse,
    Boolean personalCollect,
    Boolean locationBasedServiceUse,
    Boolean electronicFinanceUse,
    Boolean personalCollectForMarketing
) {

}
