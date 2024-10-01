package kr.co.leteatgo.user.application.dto;

import lombok.Builder;

@Builder
public record AddUserLocationMessage(
    Long locationId,
    Double lng, Double lat
) {

//  public static AddUserLocationMessage from(Location location) {
//    return new AddUserLocationMessage(
//        location.getId(),
//        location.getPoint().getLng(),
//        location.getPoint().getLat()
//    );
//  }
}
