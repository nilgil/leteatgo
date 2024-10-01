package kr.co.leteatgo.etc.application.term.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.leteatgo.etc.domain.term.TermType;

public record CreateTermRequest(
    @NotNull TermType type,
    @NotBlank String title,
    @NotBlank String key,
    @NotNull Boolean required,
    @NotNull Integer sort,
    Long parentId,
    String content
) {

}
