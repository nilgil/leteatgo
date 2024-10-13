package kr.co.leteatgo.store.application;

public class GeoUtils {

	public static int betweenDistance(Double lngA, Double latA, Double lngB, Double latB) {
		double theta = lngA - lngB;
		double dist = Math.sin(deg2rad(latA))
			* Math.sin(deg2rad(latB))
			+ Math.cos(deg2rad(latA))
			* Math.cos(deg2rad(latB))
			* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1609.344;
		return (int)Math.round(dist); //단위 meter
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
