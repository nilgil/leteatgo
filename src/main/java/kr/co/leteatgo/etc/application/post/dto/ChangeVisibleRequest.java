package kr.co.leteatgo.etc.application.post.dto;

import jakarta.validation.constraints.NotNull;

public record ChangeVisibleRequest(@NotNull Boolean visible) {

}
