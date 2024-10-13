package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import kr.co.leteatgo.store.domain.common.Email;
import kr.co.leteatgo.store.domain.common.PhoneNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreContact {

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "contact_phone_number", nullable = false))
	private PhoneNumber phoneNumber;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "contact_email"))
	private Email email;

	public StoreContact(PhoneNumber phoneNumber, Email email) {
		if (phoneNumber == null) {
			throw new IllegalArgumentException("contact phoneNumber must be not null");
		}
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
