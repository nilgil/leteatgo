package kr.co.leteatgo.etc.application.term.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record TermResponse(
	Long id,
	String title,
	String key,
	Boolean required,
	Integer sort,
	String content,
	List<TermResponse> child
) {

}
