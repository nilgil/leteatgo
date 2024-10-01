package kr.co.leteatgo.etc.presentation.term;

import common.aws.s3.PreSignedUrls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import kr.co.leteatgo.etc.application.term.TermService;
import kr.co.leteatgo.etc.application.term.dto.CreateTermRequest;
import kr.co.leteatgo.etc.application.term.dto.TermResponse;
import kr.co.leteatgo.etc.application.term.dto.UpdateTermRequest;
import kr.co.leteatgo.etc.domain.term.TermType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Term", description = "v1")
@RestController
@RequestMapping("/v1/terms")
@RequiredArgsConstructor
public class TermsControllerV1 {

  private final TermService termService;

  @Operation(summary = "약관 목록 조회")
  @GetMapping
  List<TermResponse> getTerms(TermType type) {
    return termService.getTerms(type);
  }

  @Operation(summary = "약관 생성")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  PreSignedUrls createTerm(@Valid @RequestBody CreateTermRequest request) {
    return termService.createTerm(request);
  }

  @Operation(summary = "약관 수정")
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{termId}")
  void updateTerm(@PathVariable Long termId, @Valid @RequestBody UpdateTermRequest request) {
    termService.updateTerm(termId, request);
  }

  @Operation(summary = "약관 삭제")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{termId}")
  void deleteTerm(@PathVariable Long termId) {
    termService.deleteTerm(termId);
  }
}
