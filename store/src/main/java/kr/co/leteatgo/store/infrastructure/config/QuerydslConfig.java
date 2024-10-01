package kr.co.leteatgo.store.infrastructure.config;

import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.spatial.SpatialOps;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(new QuerydslCustomTemplate(), entityManager);
  }

  private static class QuerydslCustomTemplate extends HQLTemplates {

    private QuerydslCustomTemplate() {
      super();
      add(Map.of(SpatialOps.DISTANCE, "st_distance({0}, {1})"));
      add(Map.of(SpatialOps.DISTANCE_SPHERE, "st_distancesphere({0}, {1})"));
      add(Map.of(SpatialOps.DISTANCE_SPHEROID, "st_distancespheroid({0}, {1})"));
    }
  }
}
