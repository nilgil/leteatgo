package kr.co.leteatgo.user.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZoneSimple {

  private Integer code;
  private ZoneAddress zoneAddress;
}
