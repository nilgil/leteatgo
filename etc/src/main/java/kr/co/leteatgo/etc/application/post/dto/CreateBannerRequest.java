package kr.co.leteatgo.etc.application.post.dto;


import jakarta.validation.constraints.NotNull;
import kr.co.leteatgo.etc.domain.post.MainBannerType;

public record CreateBannerRequest(@NotNull MainBannerType type, @NotNull Long id) {

}
