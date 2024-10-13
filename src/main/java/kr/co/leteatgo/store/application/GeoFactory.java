package kr.co.leteatgo.store.application;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class GeoFactory {

	private static final GeometryFactory geoFactory = new GeometryFactory();

	public static Coordinate lngLatToCoordinate(Double lng, Double lat) {
		return new Coordinate(lng, lat);
	}

	public static Point coordinateToPoint(Coordinate coordinate) {
		return geoFactory.createPoint(coordinate);
	}

	public static Point lngLatToPoint(Double lng, Double lat) {
		return coordinateToPoint(lngLatToCoordinate(lng, lat));
	}
}
