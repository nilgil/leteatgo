package kr.co.leteatgo.store.domain.store.entity;

import java.util.Map;

import jakarta.persistence.Entity;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreTermAgreements extends BaseIdEntity {

	private Boolean serviceUse; // 서비스 이용약관
	private Boolean collectPersonalInfo; // 개인정보 수집 및 이용 동의
	private Boolean collectPersonalInfoForEvent; // 서비스/이벤트 정보 제공을 위한 개인정보 수집 및 이용 동
	private Boolean receiveAdSms; // 광고성 정보 수신 동의 (SMS)
	private Boolean receiveAdEmail; // 광고성 정보 수신 동의 (이메일)
	private Boolean receiveAdCall; // 광고성 정보 수신 동의 (전화)

	@Builder
	private StoreTermAgreements(Boolean serviceUse, Boolean collectPersonalInfo,
		Boolean collectPersonalInfoForEvent, Boolean receiveAdSms, Boolean receiveAdEmail,
		Boolean receiveAdCall) {
		if (!serviceUse || !collectPersonalInfo) {
			throw new LegException(ErrorCode.BAD_REQUEST, "서비스 이용약관과 개인정보 수집 및 이용 동의는 필수 동의 항목입니다.");
		}
		this.serviceUse = true;
		this.collectPersonalInfo = true;
		this.collectPersonalInfoForEvent = collectPersonalInfoForEvent;
		this.receiveAdSms = receiveAdSms;
		this.receiveAdEmail = receiveAdEmail;
		this.receiveAdCall = receiveAdCall;
	}

	public static StoreTermAgreements from(Map<String, Boolean> termAgreementsMap) {
		return StoreTermAgreements.builder()
			.serviceUse(termAgreementsMap.get("serviceUse"))
			.collectPersonalInfo(termAgreementsMap.get("collectPersonalInfo"))
			.collectPersonalInfoForEvent(termAgreementsMap.get("collectPersonalInfoForEvent"))
			.receiveAdSms(termAgreementsMap.get("receiveAdSms"))
			.receiveAdEmail(termAgreementsMap.get("receiveAdEmail"))
			.receiveAdCall(termAgreementsMap.get("receiveAdCall"))
			.build();
	}
}
