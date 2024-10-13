package kr.co.leteatgo.auth.domain.credential;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Phone {

	@Embedded
	private PhoneNumber number;
	@Embedded
	private DeviceToken deviceToken;

	public Phone(PhoneNumber phoneNumber) {
		this(phoneNumber, null);
	}

	private Phone(PhoneNumber number, DeviceToken deviceToken) {
		this.number = number;
		this.deviceToken = deviceToken;
	}

	public boolean matchDeviceToken(DeviceToken deviceToken) {
		return this.deviceToken.equals(deviceToken);
	}

	public void updateDeviceToken(DeviceToken deviceToken) {
		this.deviceToken = deviceToken;
	}
}
