package kr.co.leteatgo.store.application.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record MenuSetResponse(
    Long id,
    String name,
    List<MenuGroupResponse> groups
) {

}
