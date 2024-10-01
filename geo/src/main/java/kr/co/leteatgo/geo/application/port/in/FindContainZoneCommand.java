package kr.co.leteatgo.geo.application.port.in;

import kr.co.leteatgo.geo.common.SelfValidating;
import kr.co.leteatgo.geo.domain.Coordinate;
import lombok.Getter;

@Getter
public class FindContainZoneCommand extends SelfValidating<FindContainZoneCommand> {

  private final Coordinate coordinate;

  public FindContainZoneCommand(Coordinate coordinate) {
    this.coordinate = coordinate;
  }
}
