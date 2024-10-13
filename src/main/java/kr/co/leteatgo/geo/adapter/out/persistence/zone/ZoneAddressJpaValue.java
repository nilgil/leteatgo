package kr.co.leteatgo.geo.adapter.out.persistence.zone;

import java.util.StringTokenizer;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ZoneAddressJpaValue {

	private String fullAddress;
	private String city;
	private String subCity;
	private String district;
	private String town;

	private ZoneAddressJpaValue(String fullAddress) {
		StringTokenizer st = new StringTokenizer(fullAddress);
		String initialCity = st.nextToken();
		String initialDistrict = st.nextToken();
		String initialTown = st.nextToken();
		String correctedCity = correctCity(initialCity);
		String correctedDistrict = correctDistrict(initialDistrict, correctedCity);

		this.city = correctedCity;
		this.district = correctedDistrict;
		this.town = initialTown;
		this.subCity = correctedSubCity(initialDistrict);
		this.fullAddress = buildFullAddress();
	}

	public static ZoneAddressJpaValue of(String fullAddress) {
		return new ZoneAddressJpaValue(fullAddress);
	}

	private String correctCity(String city) {
		if (city.length() <= 3) {
			return city;
		}
		if (city.contains("서울특별시") || city.contains("광역시") || city.contains("제주특별자치도")) {
			return city.substring(0, 2);
		}
		if (city.length() == 4) {
			return Character.toString(city.charAt(0)) + city.charAt(2);
		}
		if (city.contains("세종특별자치시")) {
			return "세종시";
		}
		return city;
	}

	private String correctDistrict(String district, String city) {
		if (city.equals("세종시")) {
			return "";
		}
		if (district.length() <= 4) {
			return district;
		}
		int si = district.indexOf("시");
		if (si >= 2 && si != district.length() - 1) {
			return district.substring(si + 1);
		}
		return district;
	}

	private String correctedSubCity(String district) {
		int si = district.indexOf("시");
		if (si >= 2 && si != district.length() - 1) {
			return district.substring(0, si + 1);
		}
		return "";
	}

	private String buildFullAddress() {
		StringBuilder sb = new StringBuilder();
		sb.append(city);
		if (!subCity.isEmpty()) {
			sb.append(" ").append(subCity);
		}
		sb.append(" ").append(district).append(" ").append(town);
		return sb.toString();
	}
}
