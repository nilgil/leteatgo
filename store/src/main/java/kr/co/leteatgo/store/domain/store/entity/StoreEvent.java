package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreEvent extends BaseIdEntity {

  private Boolean active;
  @Column(length = 500)
  private String content;

  private StoreEvent(Boolean active, String content) {
    this.active = active;
    this.content = content;
  }

  public static StoreEvent init() {
    return new StoreEvent(false, "");
  }
}
