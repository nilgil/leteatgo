package kr.co.leteatgo.auth.domain.common;

import java.util.List;
import java.util.UUID;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import kr.co.leteatgo.common.auth.jwt.Account;
import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = BaseEntity.class)
@Audited(withModifiedFlag = true)
@MappedSuperclass
public abstract class LegAccount extends BaseEntity implements Account {

	@Id
	private UUID id;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<String> roles;
	private Boolean active;
	private Boolean locked;

	protected LegAccount(List<String> roles) {
		this(roles, true);
	}

	protected LegAccount(List<String> roles, Boolean active) {
		this(UUID.randomUUID(), roles, active);
	}

	protected LegAccount(UUID id, List<String> roles, Boolean active) {
		this(id, roles, active, false);
	}

	public LegAccount(UUID id, List<String> roles, Boolean active, Boolean locked) {
		this.id = id;
		this.roles = roles;
		this.active = active;
		this.locked = locked;
	}

	@Override
	public UUID id() {
		return this.id;
	}

	@Override
	public List<String> roles() {
		return this.roles;
	}

	public void active() {
		this.active = true;
	}

	public void lock() {
		this.locked = true;
	}

	public void unlock() {
		this.locked = false;
	}

	public void validAccount() {
		if (!this.active) {
			throw new LegException(ErrorCode.NOT_ACTIVATED_ACCOUNT);
		}
		if (this.locked) {
			throw new LegException(ErrorCode.BLOCK_ACCOUNT);
		}
	}
}
