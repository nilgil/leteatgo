package kr.co.leteatgo.geo.application.dto;


import kr.co.leteatgo.geo.domain.Zone;
import lombok.Data;

@Data
public class ZoneResponse {

  private String code;
  private String fullAddress;
  private String city;
  private String subCity;
  private String district;
  private String town;

  public static ZoneResponse from(Zone zone) {
    return new ZoneResponse(zone);
  }

  private ZoneResponse(Zone zone) {
    this.code = zone.getCode();
    this.fullAddress = zone.getFullAddress();
    this.city = zone.getCity();
    this.subCity = zone.getSubCity();
    this.district = zone.getDistrict();
    this.town = zone.getTown();
  }
}
