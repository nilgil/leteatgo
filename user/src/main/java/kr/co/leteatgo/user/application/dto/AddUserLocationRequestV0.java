package kr.co.leteatgo.user.application.dto;

public record AddUserLocationRequestV0(
    String alias,
    Boolean marked,
    AddressDtoV0 address
) {

}
