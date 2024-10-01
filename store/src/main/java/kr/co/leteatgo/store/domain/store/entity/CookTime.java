package kr.co.leteatgo.store.domain.store.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CookTime {

  private int minute;

  public CookTime(int minute) {
    if (minute % 5 != 0) {
      throw new IllegalArgumentException("조리 시간은 5분 단위로 설정 할 수 있습니다.");
    }
    this.minute = minute;
  }
}
