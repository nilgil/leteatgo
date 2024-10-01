package kr.co.leteatgo.store.domain.store.enums;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum StoreStatus {
  OPEN("오픈"),
  BREAK("휴식"),
  CLOSE("마감");

  private static final Map<String, StoreStatus> descriptions = Collections.unmodifiableMap(
      Stream.of(values())
          .collect(Collectors.toMap(StoreStatus::getDescription, Function.identity())));

  private final String description;

  StoreStatus(String description) {
    this.description = description;
  }

  public static StoreStatus find(String description) {
    return Optional.ofNullable(descriptions.get(description)).orElse(CLOSE);
  }
}
