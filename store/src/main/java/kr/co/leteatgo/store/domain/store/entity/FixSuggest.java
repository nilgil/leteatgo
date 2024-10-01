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
public class FixSuggest extends BaseIdEntity {

  @Column(length = 3000)
  private String content;
//  @ManyToOne
//  private Store store;
}
