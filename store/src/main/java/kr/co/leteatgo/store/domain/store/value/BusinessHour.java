package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalTime;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.store.entity.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BusinessHour extends BaseIdEntity {

  @Enumerated(EnumType.STRING)
  private Week beginWeek;
  @Enumerated(EnumType.STRING)
  private Week endWeek;
  private LocalTime openAt;
  private LocalTime closeAt;
  private LocalTime breakStartAt;
  private LocalTime breakEndAt;
  @ManyToOne
  private Store store;

  @Builder
  private BusinessHour(Week beginWeek, Week endWeek, LocalTime openAt, LocalTime closeAt,
      LocalTime breakStartAt, LocalTime breakEndAt) {
    this.beginWeek = beginWeek;
    this.endWeek = endWeek;
    this.openAt = openAt;
    this.closeAt = closeAt;
    this.breakStartAt = breakStartAt;
    this.breakEndAt = breakEndAt;
  }
}
