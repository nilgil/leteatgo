package kr.co.leteatgo.etc.presentation.post;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import kr.co.leteatgo.etc.application.post.dto.DetailNoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@Tag(name = "Notice", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeControllerV0 {

  private final NoticeControllerV1 noticeController;

  @Operation(summary = "공지 목록 조회")
  @GetMapping
  Slice<SimpleNoticeResponseV0> getNoticeSimples(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
      @ParameterObject Pageable pageable) {
    return noticeController.getSimpleNotices(pageable).map(simpleNoticeResponse ->
        new SimpleNoticeResponseV0(
            simpleNoticeResponse.id(),
            simpleNoticeResponse.title(),
            simpleNoticeResponse.visible(),
            simpleNoticeResponse.bannerImage(),
            simpleNoticeResponse.createdAt()
        ));
  }

  private record SimpleNoticeResponseV0(Long id, String title, Boolean pub, String banner,
                                        LocalDateTime createdAt) {

  }

  @Operation(summary = "공지 상세 조회")
  @GetMapping("/{noticeId}")
  DetailNoticeResponseV0 getNoticeDetail(@PathVariable Long noticeId) {
    DetailNoticeResponse response = noticeController.getDetailNotice(noticeId);
    return new DetailNoticeResponseV0(
        response.id(),
        response.title(),
        response.visible(),
        response.bannerImage(),
        response.content(),
        response.createdAt()
    );
  }

  private record DetailNoticeResponseV0(Long id, String title, Boolean pub, String banner,
                                        String content, LocalDateTime createdAt) {

  }
}
