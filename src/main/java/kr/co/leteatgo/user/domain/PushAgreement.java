package kr.co.leteatgo.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PushAgreement {

	@Column(name = "push_agreement")
	private Boolean value;

	public PushAgreement(Boolean value) {
		this.value = value;
	}

	public static PushAgreement init() {
		return new PushAgreement(false);
	}

	public void changeAgreement(Boolean value) {
		this.value = value;
	}
}
