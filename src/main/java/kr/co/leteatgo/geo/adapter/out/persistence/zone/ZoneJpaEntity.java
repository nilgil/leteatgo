package kr.co.leteatgo.geo.adapter.out.persistence.zone;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.co.leteatgo.geo.domain.Zone;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Zone")
public class ZoneJpaEntity {

	@Id
	private String code;
	@Embedded
	private ZoneAddressJpaValue address;
	private Polygon bound;
	private Point center;

	@Builder
	private ZoneJpaEntity(String code, ZoneAddressJpaValue address, Polygon bound, Point center) {
		this.code = code;
		this.address = address;
		this.bound = bound;
		this.center = center;
	}

	public Zone toZone() {
		return Zone.builder()
			.code(code)
			.fullAddress(address.getFullAddress())
			.city(address.getCity())
			.subCity(address.getSubCity())
			.district(address.getDistrict())
			.town(address.getTown())
			.build();
	}
}