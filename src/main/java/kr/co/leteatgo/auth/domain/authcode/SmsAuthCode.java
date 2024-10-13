package kr.co.leteatgo.auth.domain.authcode;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.leteatgo.auth.domain.common.BaseEntity;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SmsAuthCode extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PhoneNumber phoneNumber;
	private AuthCode authCode;

	public SmsAuthCode(PhoneNumber phoneNumber, AuthCode authCode) {
		this.phoneNumber = phoneNumber;
		this.authCode = authCode;
	}

	public boolean isCreatedBefore(LocalDateTime dateTime) {
		return this.getCreatedAt().isBefore(dateTime);
	}

	public String phoneNumber() {
		return phoneNumber.getValue();
	}

	public String authCode() {
		return authCode.getValue();
	}
}
