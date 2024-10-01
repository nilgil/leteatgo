package kr.co.leteatgo.geo.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Zone {

  private final String code;
  private final String fullAddress;
  private final String city;
  private final String subCity;
  private final String district;
  private final String town;

  @Builder
  private Zone(String code, String fullAddress, String city, String subCity, String district,
      String town) {
    this.code = code;
    this.fullAddress = fullAddress;
    this.city = city;
    this.subCity = subCity;
    this.district = district;
    this.town = town;
  }
}
