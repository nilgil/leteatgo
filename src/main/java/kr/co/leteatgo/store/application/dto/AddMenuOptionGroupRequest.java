package kr.co.leteatgo.store.application.dto;

public record AddMenuOptionGroupRequest(
	String name,
	Integer minLimit,
	Integer maxLimit,
	Integer sort
) {

}
