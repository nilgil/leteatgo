package kr.co.leteatgo.geo.application;

import org.locationtech.jts.geom.Point;

public class GeoUtils {

	public static int distanceMeter(Point location1, Point location2) {
		double theta = location1.getX() - location2.getX();
		double dist = Math.sin(deg2rad(location1.getY()))
			* Math.sin(deg2rad(location2.getY()))
			+ Math.cos(deg2rad(location1.getY()))
			* Math.cos(deg2rad(location2.getY()))
			* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1609.344;
		return (int)Math.round(dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
