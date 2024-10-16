package kr.co.leteatgo.geo.application.port.out;

import org.springframework.data.domain.Pageable;

import kr.co.leteatgo.geo.common.Scroll;
import kr.co.leteatgo.geo.domain.Coordinate;
import kr.co.leteatgo.geo.domain.Zone;

public interface ZoneReadPort {

	Scroll<Zone> searchNearlyZones(Pageable pageable, Coordinate coordinate, String search);

	Zone findContainZone(Coordinate coordinate);
}
