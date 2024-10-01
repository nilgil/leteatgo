package kr.co.leteatgo.user.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Embeddable
public class Coordinate {

  private Double lng;
  private Double lat;

  public Coordinate(Double lng, Double lat) {
    this.lng = lng;
    this.lat = lat;
  }
}
