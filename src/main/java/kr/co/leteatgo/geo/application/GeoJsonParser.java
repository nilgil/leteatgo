package kr.co.leteatgo.geo.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LngLatAlt;
import org.geojson.MultiPolygon;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.geo.adapter.out.persistence.zone.ZoneAddressJpaValue;
import kr.co.leteatgo.geo.adapter.out.persistence.zone.ZoneJpaEntity;

public class GeoJsonParser {

	private static final String FEATURE_CODE = "adm_cd8";
	private static final String FEATURE_ADDRESS = "adm_nm";

	public static List<ZoneJpaEntity> getZonesByGeoJson(MultipartFile geoJsonFile) {

		List<Feature> features;

		try (InputStream inputStream = geoJsonFile.getInputStream()) {
			features = geoJsonToFeatures(inputStream);
		} catch (IOException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR);
		}

		return features.stream().map(GeoJsonParser::featureToZone).toList();
	}

	private static List<Feature> geoJsonToFeatures(InputStream inputStream) {
		try {
			FeatureCollection featureCollection = new ObjectMapper()
				.readValue(inputStream, FeatureCollection.class);
			return featureCollection.getFeatures();
		} catch (IOException e) {
			throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR);
		}
	}

	private static ZoneJpaEntity featureToZone(Feature feature) {
		String code = feature.getProperties().get(FEATURE_CODE).toString();
		String fullAddress = feature.getProperties().get(FEATURE_ADDRESS).toString();
		ZoneAddressJpaValue address = ZoneAddressJpaValue.of(fullAddress);
		Polygon bound = featureToPolygon(feature);
		Point center = bound.getCentroid();
		return ZoneJpaEntity.builder()
			.code(code)
			.address(address)
			.bound(bound)
			.center(center)
			.build();
	}

	private static Polygon featureToPolygon(Feature feature) {
		MultiPolygon multiPolygon = (MultiPolygon)feature.getGeometry();
		return multiPolygonToPolygon(multiPolygon);
	}

	private static Polygon multiPolygonToPolygon(MultiPolygon multiPolygon) {
		List<LngLatAlt> lngLatAlts = multiPolygon.getCoordinates().get(0).get(0);
		Coordinate[] coordinates = lngLatAlts.stream()
			.map(lngLatAlt -> new Coordinate(lngLatAlt.getLongitude(), lngLatAlt.getLatitude()))
			.toArray(Coordinate[]::new);
		return GeoFactory.INSTANCE.createPolygon(coordinates);
	}
}
