package kr.co.leteatgo.store.domain.store.value;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ChoiceLimit {

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "choice_min"))
  private PositiveOrZero min;
  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "choice_max"))
  private PositiveOrZero max;

  public ChoiceLimit(PositiveOrZero min, PositiveOrZero max) {
    this.min = min;
    this.max = max;
  }

  public static ChoiceLimit valueOf(PositiveOrZero min, PositiveOrZero max) {
    return new ChoiceLimit(min, max);
  }
}
