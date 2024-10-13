package kr.co.leteatgo.geo.adapter.out.persistence.zone;

import java.util.Collection;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import kr.co.leteatgo.geo.application.GeoFactory;
import kr.co.leteatgo.geo.application.port.out.ZoneReadPort;
import kr.co.leteatgo.geo.application.port.out.ZoneWritePort;
import kr.co.leteatgo.geo.common.Scroll;
import kr.co.leteatgo.geo.domain.Coordinate;
import kr.co.leteatgo.geo.domain.Zone;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ZonePersistenceAdapter implements ZoneReadPort, ZoneWritePort {

	private final ZoneRepository zoneRepository;

	@Override
	public Scroll<Zone> searchNearlyZones(Pageable pageable, Coordinate coordinate, String search) {
		Point location = GeoFactory.lngLatToPoint(coordinate.getLng(), coordinate.getLat());

		if (StringUtils.hasText(search)) {
			search = "%" + search + "%";
			Slice<Zone> zones = zoneRepository.searchNearlyZonesWithSearch(pageable, location, search)
				.map(ZoneJpaEntity::toZone);
			return new Scroll<>(zones.getContent(), zones.hasNext());
		}

		Slice<Zone> zones = zoneRepository.searchNearlyZones(pageable, location)
			.map(ZoneJpaEntity::toZone);
		return new Scroll<>(zones.getContent(), zones.hasNext());
	}

	@Override
	public Zone findContainZone(Coordinate coordinate) {
		Point location = GeoFactory.lngLatToPoint(coordinate.getLng(), coordinate.getLat());
		ZoneJpaEntity zoneJpaEntity = zoneRepository.findContainZone(location);
		return zoneJpaEntity.toZone();
	}

	@Override
	public void deleteAll() {
		zoneRepository.deleteAll();
	}

	@Override
	public int saveAll(Collection<ZoneJpaEntity> zones) {
		return zoneRepository.saveAll(zones).size();
	}
}
