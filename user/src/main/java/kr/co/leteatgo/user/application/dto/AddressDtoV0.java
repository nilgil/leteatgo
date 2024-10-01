package kr.co.leteatgo.user.application.dto;

import lombok.Builder;

@Builder
public record AddressDtoV0(
    String regionAddress,
    String roadAddress,
    String locationName,
    String detail,
    CoordinateDto coordinate
) {

}
