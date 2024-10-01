package kr.co.leteatgo.user.application.dto;

import lombok.Builder;

@Builder
public record UserLocationResponseV0(
    Long id,
    String alias,
    Boolean marked,
    Boolean active,
    AddressDtoV0 address
) {

}
