package kr.co.leteatgo.store.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record CreateImageSubmissionRequest(@NotEmpty List<String> images) {

}
