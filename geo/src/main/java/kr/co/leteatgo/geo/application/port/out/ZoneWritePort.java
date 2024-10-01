package kr.co.leteatgo.geo.application.port.out;

import java.util.Collection;
import kr.co.leteatgo.geo.adapter.out.persistence.zone.ZoneJpaEntity;

public interface ZoneWritePort {

  void deleteAll();

  int saveAll(Collection<ZoneJpaEntity> zones);
}
