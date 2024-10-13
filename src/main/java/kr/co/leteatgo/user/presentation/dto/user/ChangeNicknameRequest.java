package kr.co.leteatgo.user.presentation.dto.user;

import jakarta.validation.constraints.NotBlank;

public record ChangeNicknameRequest(@NotBlank String nickname) {

}
