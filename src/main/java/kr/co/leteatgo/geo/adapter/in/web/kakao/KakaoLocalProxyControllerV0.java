package kr.co.leteatgo.geo.adapter.in.web.kakao;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Deprecated
@Tag(name = "KakaoLocal Api Proxy", description = "v0")
@RequestMapping("/kakao/proxy")
@RequiredArgsConstructor
@RestController
public class KakaoLocalProxyControllerV0 {

	private final KakaoLocalProxyControllerV1 currentVersionController;

	@GetMapping("/address")
	Mono<Resource> getAddressesByAddress(@ParameterObject SearchByAddressRequest request) {
		return currentVersionController.getAddressesByAddress(request);
	}

	@GetMapping("/keyword")
	Mono<Resource> getAddressesByKeyword(@ParameterObject SearchByKeywordRequest request) {
		return currentVersionController.getAddressesByKeyword(request);
	}
}
