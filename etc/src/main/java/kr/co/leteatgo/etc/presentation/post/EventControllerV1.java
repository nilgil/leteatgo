package kr.co.leteatgo.etc.presentation.post;


import common.aws.s3.PreSignedUrls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.etc.application.post.EventService;
import kr.co.leteatgo.etc.application.post.dto.ChangeVisibleRequest;
import kr.co.leteatgo.etc.application.post.dto.DetailEventResponse;
import kr.co.leteatgo.etc.application.post.dto.SimpleEventResponse;
import kr.co.leteatgo.etc.application.post.dto.WriteEventRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Event", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/events")
@RestController
public class EventControllerV1 {

  private final EventService eventService;

  @Operation(summary = "이벤트 목록 조회")
  @GetMapping
  Slice<SimpleEventResponse> getSimpleEvents(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
      @ParameterObject Pageable pageable
  ) {
    return eventService.getSimpleEvents(pageable);
  }

  @Operation(summary = "이벤트 상세 조회")
  @GetMapping("/{eventId}")
  DetailEventResponse getDetailEvent(@PathVariable Long eventId) {
    return eventService.getDetailEvent(eventId);
  }

  @Operation(summary = "이벤트 생성")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  PreSignedUrls writeEvent(@Valid @RequestBody WriteEventRequest reqDto) {
    return eventService.writeEvent(reqDto);
  }

  @Operation(summary = "이벤트 공개 설정")
  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("/{eventId}/visible")
  void changeEventVisible(
      @PathVariable Long eventId,
      @Valid @RequestBody ChangeVisibleRequest request
  ) {
    eventService.changeEventVisible(eventId, request.visible());
  }

  @Operation(summary = "이벤트 삭제")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{eventId}")
  void deleteEvent(@PathVariable Long eventId) {
    eventService.deleteEvent(eventId);
  }
}
