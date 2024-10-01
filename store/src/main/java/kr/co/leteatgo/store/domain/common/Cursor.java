package kr.co.leteatgo.store.domain.common;

import java.util.List;
import lombok.Builder;

public record Cursor<T, ID>(
    List<T> content,
    Integer size,
    Integer numberOfElements,
    Boolean first,
    Boolean last,
    ID prevId,
    ID lastId
) {

  @Builder
  private Cursor(List<T> content, Integer size, ID prevId, ID lastId) {
    this(content, size, content.size(), prevId == null, lastId == null, prevId, lastId);
  }
}
