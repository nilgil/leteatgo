package kr.co.leteatgo.etc.presentation.post;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.etc.application.post.BannerService;
import kr.co.leteatgo.etc.application.post.dto.BannerResponse;
import kr.co.leteatgo.etc.application.post.dto.CreateBannerRequest;
import lombok.RequiredArgsConstructor;

@Tag(name = "Main Banner", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/banners")
@RestController
public class MainBannerControllerV1 {

	private final BannerService bannerService;

	@Operation(summary = "배너 목록 조회")
	@GetMapping
	Slice<BannerResponse> getBanners(
		@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
		@ParameterObject Pageable pageable
	) {
		return bannerService.getBanners(pageable);
	}

	@Operation(summary = "배너 추가", description = "추가 배너가 첫번째 순서")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	void addBanner(@Valid @RequestBody CreateBannerRequest request) {
		bannerService.addBanner(request);
	}

	@Operation(summary = "배너 재설정", description = "기존 배너 목록은 삭제")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	void resetBanners(@RequestBody List<CreateBannerRequest> requests) {
		bannerService.resetBanners(requests);
	}
}
