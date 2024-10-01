package kr.co.leteatgo.store.application.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateImageSubmissionRequest(@NotEmpty List<String> images) {

}
