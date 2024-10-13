package kr.co.leteatgo.geo.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import kr.co.leteatgo.geo.common.Scroll;
import kr.co.leteatgo.geo.domain.Coordinate;
import kr.co.leteatgo.geo.domain.Zone;

public interface ZoneUseCase {

	Scroll<Zone> searchNearlyZones(SearchNearlyZonesCommand command);

	Zone findContainZone(Coordinate coordinate);

	int resetZoneData(MultipartFile zoneData);
}
