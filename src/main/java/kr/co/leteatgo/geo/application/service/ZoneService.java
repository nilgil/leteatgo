package kr.co.leteatgo.geo.application.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.geo.adapter.out.persistence.zone.ZoneJpaEntity;
import kr.co.leteatgo.geo.application.GeoJsonParser;
import kr.co.leteatgo.geo.application.port.in.SearchNearlyZonesCommand;
import kr.co.leteatgo.geo.application.port.in.ZoneUseCase;
import kr.co.leteatgo.geo.application.port.out.ZoneReadPort;
import kr.co.leteatgo.geo.application.port.out.ZoneWritePort;
import kr.co.leteatgo.geo.common.Scroll;
import kr.co.leteatgo.geo.domain.Coordinate;
import kr.co.leteatgo.geo.domain.Zone;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ZoneService implements ZoneUseCase {

	private final ZoneReadPort zoneReadPort;
	private final ZoneWritePort zoneWritePort;

	@Override
	public Scroll<Zone> searchNearlyZones(SearchNearlyZonesCommand command) {
		Pageable pageable = command.getPageable();
		return zoneReadPort.searchNearlyZones(pageable, command.getCoordinate(), command.getSearch());
	}

	@Override
	public Zone findContainZone(Coordinate coordinate) {
		return zoneReadPort.findContainZone(coordinate);
	}

	@Transactional
	@Override
	public int resetZoneData(MultipartFile zoneData) {

		if (zoneData.isEmpty()) {
			return -1;
		}

		List<ZoneJpaEntity> zoneJpaEntities = GeoJsonParser.getZonesByGeoJson(zoneData);
		if (zoneJpaEntities.size() < 3000) {
			throw new LegException(ErrorCode.BAD_REQUEST,
				"data count is lower than 3000, please contact us");
		}

		zoneWritePort.deleteAll();
		return zoneWritePort.saveAll(zoneJpaEntities);
	}
}
