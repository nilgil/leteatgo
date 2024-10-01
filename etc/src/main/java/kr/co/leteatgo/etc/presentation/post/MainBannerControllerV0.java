package kr.co.leteatgo.etc.presentation.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.etc.domain.post.MainBannerType;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Deprecated
@Tag(name = "Main Banner", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/banners")
@RestController
public class MainBannerControllerV0 {

  private final MainBannerControllerV1 bannerController;

  @Operation(summary = "배너 목록 조회")
  @GetMapping
  Slice<BannerResponseV0> getBannerList(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
      @ParameterObject Pageable pageable) {
    return bannerController.getBanners(pageable).map(bannerResponse ->
        new BannerResponseV0(
            new BannerInfoV0(bannerResponse.type(), bannerResponse.id()),
            bannerResponse.visible(),
            bannerResponse.bannerImage()
        ));
  }

  private record BannerResponseV0(BannerInfoV0 info, Boolean pub, String banner) {

  }

  private record BannerInfoV0(MainBannerType type, Long id) {

  }
}
