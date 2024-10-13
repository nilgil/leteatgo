package kr.co.leteatgo.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZoneAddress {

	private String fullAddress;
	private String city;
	private String subCity;
	private String district;
	private String town;
}
