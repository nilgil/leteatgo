package kr.co.leteatgo.store.domain.common;

import am.ik.timeflake.Timeflake;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseUuidEntity extends BaseEntity {

  @Id
  private UUID id;

  @PrePersist
  public void init() {
    this.id = Timeflake.generate().toUuid();
  }
}
