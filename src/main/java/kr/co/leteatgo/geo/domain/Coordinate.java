package kr.co.leteatgo.geo.domain;

import lombok.Getter;

@Getter
public class Coordinate {

	private final Double lng;
	private final Double lat;

	private Coordinate(Double lng, Double lat) {
		this.lng = lng;
		this.lat = lat;
	}

	public static Coordinate of(Double lng, Double lat) {
		return new Coordinate(lng, lat);
	}
}
