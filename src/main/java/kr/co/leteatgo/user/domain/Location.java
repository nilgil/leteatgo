package kr.co.leteatgo.user.domain;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.locationtech.jts.geom.Point;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Location extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String alias;
	private Boolean marked;
	private Boolean active;
	@Embedded
	private Address address;
	private Point point;

	@ManyToOne
	private User user;

	@Builder
	private Location(String alias, Boolean marked, Boolean active, Address address,
		Point point, User user) {
		this.alias = alias;
		this.marked = marked;
		this.active = active;
		this.address = address;
		this.point = point;
		this.user = user;
	}

	public boolean isMarkedBy(String alias) {
		return this.alias.equals(alias);
	}

	public void activeLocation(Boolean active) {
		this.active = active;
	}
}
