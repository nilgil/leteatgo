package kr.co.leteatgo.store.domain.store.entity;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.store.value.Address;
import kr.co.leteatgo.store.domain.store.value.Town;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreLocation extends BaseIdEntity {

	@Embedded
	private Town town;
	@Embedded
	private Address address;
	private Point point;

	@Builder
	private StoreLocation(Town town, Address address, Point point) {
		this.town = town;
		this.address = address;
		this.point = point;
	}

	public String fullAddress() {
		return this.address.fullAddress();
	}
}
