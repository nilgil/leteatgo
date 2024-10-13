package kr.co.leteatgo.etc.presentation.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.etc.application.post.dto.DetailEventResponse;
import lombok.RequiredArgsConstructor;

@Deprecated
@Tag(name = "Event", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/events")
@RestController
public class EventControllerV0 {

	private final EventControllerV1 eventController;

	@Operation(summary = "이벤트 목록 조회")
	@GetMapping
	Slice<SimpleEventResponseV0> getSimpleEvents(
		@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
		@ParameterObject Pageable pageable) {
		return eventController.getSimpleEvents(pageable).map(simpleEventResponse ->
			new SimpleEventResponseV0(
				simpleEventResponse.id(),
				simpleEventResponse.title(),
				simpleEventResponse.visible(),
				simpleEventResponse.bannerImage(),
				simpleEventResponse.startDateTime(),
				simpleEventResponse.endDateTime()
			));
	}

	@Operation(summary = "이벤트 상세 조회")
	@GetMapping("/{eventId}")
	DetailEventResponseV0 getDetailEvent(@PathVariable Long eventId) {
		DetailEventResponse response = eventController.getDetailEvent(eventId);
		return new DetailEventResponseV0(
			response.id(),
			response.title(),
			response.visible(),
			response.startDateTime(),
			response.endDateTime(),
			response.bannerImage(),
			response.contentImages()
		);
	}

	private record SimpleEventResponseV0(Long id, String title, Boolean pub, String banner,
										 LocalDateTime startDateTime, LocalDateTime endDateTime) {

	}

	private record DetailEventResponseV0(Long id, String title, Boolean pub,
										 LocalDateTime startDateTime, LocalDateTime endDateTime,
										 String banner, List<String> images) {

	}
}
