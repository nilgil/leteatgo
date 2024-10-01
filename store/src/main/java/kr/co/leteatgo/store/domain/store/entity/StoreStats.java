package kr.co.leteatgo.store.domain.store.entity;


import jakarta.persistence.Entity;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreStats extends BaseIdEntity {

  private Long viewCount;
  private Long orderCount;

  private StoreStats(Long viewCount, Long orderCount) {
    this.viewCount = viewCount;
    this.orderCount = orderCount;
  }

  public static StoreStats init() {
    return new StoreStats(0L, 0L);
  }
}
