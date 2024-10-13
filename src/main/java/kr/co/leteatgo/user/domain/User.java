package kr.co.leteatgo.user.domain;

import java.util.UUID;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import am.ik.timeflake.Timeflake;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kr.co.leteatgo.user.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = BaseEntity.class)
@Audited(withModifiedFlag = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Id
	private UUID id;
	@Embedded
	private PhoneNumber phoneNumber;
	@Embedded
	private Nickname nickname;
	@Embedded
	private PushAgreement pushAgreement;
	@Embedded
	private Email email;
	@Embedded
	private ProfileImage profileImage;
	@Embedded
	private Locations locations = new Locations();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private TermAgreements termAgreements;

	@Builder
	private User(PhoneNumber phoneNumber, Nickname nickname, PushAgreement pushAgreement) {
		this.id = Timeflake.generate().toUuid();
		this.phoneNumber = phoneNumber;
		this.nickname = nickname;
		this.pushAgreement = pushAgreement;
		this.locations = new Locations();
	}

	public void changeNickname(Nickname nickname) {
		this.nickname = nickname;
	}

	public void changePushAgreement(PushAgreement pushAgreement) {
		this.pushAgreement = pushAgreement;
	}

	public void changeEmail(Email email) {
		this.email = email;
	}

	public void changeProfileImage(ProfileImage profileImage) {
		this.profileImage = profileImage;
	}

	public void addLocation(Location location) {
		this.locations.addLocation(location);
	}

	public void removeLocation(Location location) {
		this.locations.removeLocation(location);
	}

	public void updateTermAgreements(TermAgreements termAgreements) {
		this.termAgreements = termAgreements;
	}
}
