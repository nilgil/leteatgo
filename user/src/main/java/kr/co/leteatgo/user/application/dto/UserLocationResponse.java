package kr.co.leteatgo.user.application.dto;

import kr.co.leteatgo.user.domain.Location;
import lombok.Builder;

@Builder
public record UserLocationResponse(
    Long id,
    String alias,
    Boolean marked,
    Boolean active,
    AddressDto address,
    CoordinateDto coordinate
) {

  public static UserLocationResponse from(Location location) {
    return UserLocationResponse.builder()
        .id(location.getId())
        .alias(location.getAlias())
        .marked(location.getMarked())
        .active(location.getActive())
        .address(AddressDto.from(location.getAddress()))
        .coordinate(new CoordinateDto(location.getPoint().getX(), location.getPoint().getY()))
        .build();
  }
}
