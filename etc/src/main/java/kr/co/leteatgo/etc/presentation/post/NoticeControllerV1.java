package kr.co.leteatgo.etc.presentation.post;


import common.aws.s3.PreSignedUrls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.leteatgo.etc.application.post.NoticeService;
import kr.co.leteatgo.etc.application.post.dto.ChangeVisibleRequest;
import kr.co.leteatgo.etc.application.post.dto.DetailNoticeResponse;
import kr.co.leteatgo.etc.application.post.dto.SimpleNoticeResponse;
import kr.co.leteatgo.etc.application.post.dto.WriteNoticeRequest;
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

@Tag(name = "Notice", description = "v1")
@RestController
@RequestMapping("/v1/notices")
@RequiredArgsConstructor
public class NoticeControllerV1 {

  private final NoticeService noticeService;

  @Operation(summary = "공지 목록 조회")
  @GetMapping
  Slice<SimpleNoticeResponse> getSimpleNotices(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
      @ParameterObject Pageable pageable
  ) {
    return noticeService.getSimpleNotices(pageable);
  }

  @Operation(summary = "공지 상세 조회")
  @GetMapping("/{noticeId}")
  DetailNoticeResponse getDetailNotice(@PathVariable Long noticeId) {
    return noticeService.getDetailNotice(noticeId);
  }

  @Operation(summary = "공지 생성")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  PreSignedUrls writeNotice(@Valid @RequestBody WriteNoticeRequest request) {
    return noticeService.writeNotice(request);
  }

  @Operation(summary = "공지 공개 설정")
  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("/{noticeId}/visible")
  void changeNoticeVisible(
      @PathVariable Long noticeId,
      @Valid @RequestBody ChangeVisibleRequest request
  ) {
    noticeService.changeNoticeVisible(noticeId, request.visible());
  }

  @Operation(summary = "공지 삭제")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{noticeId}")
  void deleteNotice(@PathVariable Long noticeId) {
    noticeService.deleteNotice(noticeId);
  }
}
