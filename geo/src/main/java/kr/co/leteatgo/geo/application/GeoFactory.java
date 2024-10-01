package kr.co.leteatgo.geo.application;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class GeoFactory {

  public static final GeometryFactory INSTANCE = new GeometryFactory();

  public static Coordinate lngLatToCoordinate(Double lng, Double lat) {
    return new Coordinate(lng, lat);
  }

  public static Point coordinateToPoint(Coordinate coordinate) {
    return INSTANCE.createPoint(coordinate);
  }

  public static Point lngLatToPoint(Double lng, Double lat) {
    return coordinateToPoint(lngLatToCoordinate(lng, lat));
  }
}
