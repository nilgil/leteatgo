package kr.co.leteatgo.store.domain.store.entity;

import lombok.Getter;

@Getter
public class Distance {

  private final int distance;

  public Distance(int distance) {
    if (distance < 0) {
      throw new IllegalArgumentException("거리는 0보다 작을 수 없습니다.");
    }
    this.distance = distance;
  }
}
