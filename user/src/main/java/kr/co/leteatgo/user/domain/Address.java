package kr.co.leteatgo.user.domain;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

  private String regionAddress;
  private String roadAddress;
  private String locationName;
  private String detail;

  @Builder
  private Address(String regionAddress, String roadAddress, String locationName, String detail) {
    this.regionAddress = regionAddress;
    this.roadAddress = roadAddress;
    this.locationName = locationName;
    this.detail = detail;
  }
}
