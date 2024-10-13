package kr.co.leteatgo.auth.domain.user;

import java.util.List;
import java.util.UUID;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import kr.co.leteatgo.auth.domain.common.LegAccount;
import kr.co.leteatgo.auth.domain.credential.DeviceToken;
import kr.co.leteatgo.auth.domain.credential.Phone;
import kr.co.leteatgo.auth.domain.credential.PhoneCredential;
import kr.co.leteatgo.auth.domain.credential.PhoneNumber;
import kr.co.leteatgo.auth.domain.credential.RefreshToken;
import kr.co.leteatgo.auth.domain.credential.Refreshable;
import kr.co.leteatgo.common.auth.jwt.AccountType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = LegAccount.class)
@Audited(withModifiedFlag = true)
@Entity
public class UserAccount extends LegAccount implements Refreshable, PhoneCredential {

	@Embedded
	private Phone phone;
	@Embedded
	private RefreshToken refreshToken;

	@Builder
	private UserAccount(UUID id, PhoneNumber phoneNumber) {
		super(id, List.of(AccountType.USER.toString()), true);
		this.phone = new Phone(phoneNumber);
	}

	@Override
	public AccountType accountType() {
		return AccountType.USER;
	}

	@Override
	public boolean matchRefreshToken(RefreshToken refreshToken) {
		return this.refreshToken.equals(refreshToken);
	}

	@Override
	public void updateRefreshToken(RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public PhoneNumber phoneNumber() {
		return this.phone.getNumber();
	}

	@Override
	public DeviceToken deviceToken() {
		return this.phone.getDeviceToken();
	}

	@Override
	public boolean matchDeviceToken(DeviceToken deviceToken) {
		return this.phone.matchDeviceToken(deviceToken);
	}

	@Override
	public void updateDeviceToken(DeviceToken deviceToken) {
		this.phone.updateDeviceToken(deviceToken);
	}
}
