package kr.co.leteatgo.store.infrastructure.repository;

import static com.querydsl.spatial.locationtech.jts.JTSGeometryExpressions.asJTSGeometry;
import static kr.co.leteatgo.store.application.GeoFactory.lngLatToPoint;
import static kr.co.leteatgo.store.domain.store.entity.QStore.store;
import static kr.co.leteatgo.store.domain.store.entity.QStoreFoodType.storeFoodType;
import static kr.co.leteatgo.store.domain.store.enums.StoreSearchSort.DISTANCE;
import static kr.co.leteatgo.store.domain.store.enums.StoreSearchSort.VIEW;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.spatial.locationtech.jts.JTSGeometryExpression;
import java.util.List;
import java.util.UUID;
import kr.co.leteatgo.store.application.dto.CoordinateDto;
import kr.co.leteatgo.store.application.dto.LastStoreInfo;
import kr.co.leteatgo.store.application.dto.QLastStoreInfo;
import kr.co.leteatgo.store.application.dto.QStoreSimpleResponse;
import kr.co.leteatgo.store.application.dto.SearchStoresCriteria;
import kr.co.leteatgo.store.application.dto.StoreSimpleResponse;
import kr.co.leteatgo.store.domain.common.Cursor;
import kr.co.leteatgo.store.domain.store.enums.StoreSearchSort;
import kr.co.leteatgo.store.domain.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements StoreCustomRepository {

  private final JPAQueryFactory queryFactory;

  public Cursor<StoreSimpleResponse, UUID> searchStores(SearchStoresCriteria criteria) {

    LastStoreInfo lastStore = getLastStore(criteria);

    List<StoreSimpleResponse> results = queryFactory
        .select(new QStoreSimpleResponse(
            store.id,
            store.name.value,
            store.images.mainImage.value,
            store.location.town.name,
            getDistanceTo(criteria.coordinate()).intValue(),
            store.stats.viewCount,
            store.minCookTime,
            store.event.content))
        .from(store)
        .leftJoin(store.foodTypes.foodTypes, storeFoodType)
        .groupBy(store, store.location, store.stats, store.event)
        .where(
            statusEq(StoreStatus.OPEN),
            foodTypeIn(criteria.foodType()),
            distanceLessThan(criteria.coordinate(), criteria.maxDistanceMeter()),
            compareLastStore(lastStore, criteria.sort(), criteria.coordinate()),
            searchKeyword(criteria.search()))
        .orderBy(createOrderSpecifier(criteria.sort(), criteria.coordinate()))
        .limit(criteria.size() + 1)
        .fetch();

    boolean last = results.size() != criteria.size() + 1;
    if (!last) {
      results.remove(criteria.size().intValue());
    }
    return Cursor.<StoreSimpleResponse, UUID>builder()
        .content(results)
        .size(criteria.size())
        .prevId(criteria.prevId())
        .lastId(last ? null : results.get(results.size() - 1).storeId())
        .build();
  }

  private LastStoreInfo getLastStore(SearchStoresCriteria criteria) {
    if (criteria.prevId() == null) {
      return null;
    }
    return queryFactory
        .select(new QLastStoreInfo(
            store.id,
            store.createdAt,
            store.name.value,
            getDistanceTo(criteria.coordinate()).intValue(),
            store.stats.viewCount))
        .from(store)
        .where(
            store.id.eq(criteria.prevId())
        ).fetchOne();
  }

  private static BooleanExpression statusEq(StoreStatus status) {
    if (status == null) {
      return null;
    }
    return store.status.eq(status);
  }

  private BooleanExpression foodTypeIn(String foodTypeName) {
    if (!hasText(foodTypeName)) {
      return null;
    }
    return !foodTypeName.equals("전체") ? storeFoodType.foodType.name.in(foodTypeName) : null;
  }

  private BooleanExpression distanceLessThan(CoordinateDto coordinate, Integer maxDistanceMeter) {
    return coordinate != null ? getDistanceTo(coordinate).loe(maxDistanceMeter) : null;
  }

  @SuppressWarnings("unchecked")
  private static NumberExpression<Double> getDistanceTo(CoordinateDto coordinate) {
    return store.location.point.distanceSphere(coordinateToPointExpression(coordinate));
  }

  private BooleanExpression compareLastStore(LastStoreInfo lastStore,
      StoreSearchSort sort, CoordinateDto coordinate) {
    if (isEmpty(lastStore)) {
      return null;
    }
    if (sort == DISTANCE) {
      NumberExpression<Double> distance = getDistanceTo(coordinate);
      return distance.intValue().gt(lastStore.distance())
          .or(distance.intValue().eq(lastStore.distance())
              .and(store.createdAt.lt(lastStore.createdAt())));
    }
    if (sort == VIEW) {
      return store.stats.viewCount.lt(lastStore.viewCount())
          .or(store.stats.viewCount.eq(lastStore.viewCount())
              .and(store.createdAt.lt(lastStore.createdAt())));
    }
    return store.createdAt.lt(lastStore.createdAt());
  }

  private Predicate searchKeyword(String search) {
    return hasText(search) ?
        storeFoodType.foodType.name.contains(search).or(store.name.value.contains(search)) : null;
  }

  private static JTSGeometryExpression<Point> coordinateToPointExpression(
      CoordinateDto coordinate) {
    return asJTSGeometry(lngLatToPoint(coordinate.lng(), coordinate.lat()));
  }

  private OrderSpecifier<?>[] createOrderSpecifier(StoreSearchSort sort,
      CoordinateDto coordinate) {
    if (sort == DISTANCE) {
      return new OrderSpecifier<?>[]{
          getDistanceTo(coordinate).asc(), store.createdAt.desc()
      };
    }
    if (sort == VIEW) {
      return new OrderSpecifier<?>[]{
          store.stats.viewCount.desc(), store.createdAt.desc()
      };
    }
    return new OrderSpecifier<?>[]{
        store.createdAt.desc()
    };
  }
}
