package kr.co.leteatgo.geo.adapter.out.persistence.zone;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneJpaEntity, String> {

	@Query("select z from Zone z " +
		"where z.address.city = '서울' " +
		"order by distance(z.center, :location)")
	Slice<ZoneJpaEntity> searchNearlyZones(Pageable pageable, @Param("location") Point location);

	@Query("select z from Zone z " +
		"where z.address.city = '서울' " +
		"and z.address.town like :search " +
		"order by distance(z.center, :location)")
	Slice<ZoneJpaEntity> searchNearlyZonesWithSearch(Pageable pageable, @Param("location") Point location,
		@Param("search") String search);

	@Query("select z from Zone z where st_contains(z.bound, :location)")
	ZoneJpaEntity findContainZone(@Param("location") Point location);
}
