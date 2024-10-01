package kr.co.leteatgo.auth.presentation;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import kr.co.leteatgo.auth.application.admin.AdminAccountService;
import kr.co.leteatgo.auth.application.admin.dto.LoginAdminAccountRequest;
import kr.co.leteatgo.auth.application.admin.dto.RegisterAdminAccountRequest;
import kr.co.leteatgo.auth.application.admin.dto.ReissueAdminAccountRequest;
import kr.co.leteatgo.auth.application.common.JwtSetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Tag(name = "Admin Auth", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/admin-accounts")
@RestController
public class AdminAuthControllerV1 {

  private final AdminAccountService adminAccountService;

  @Operation(summary = "관리자 계정 추가")
  @PreAuthorize("hasRole('SUPER')")
  @PostMapping
  public void registerAdminAccount(@Valid @RequestBody RegisterAdminAccountRequest request) {
    adminAccountService.registerAdminAccount(request);
  }

  @Operation(summary = "관리자 계정 삭제")
  @PreAuthorize("hasRole('SUPER')")
  @DeleteMapping("/{adminId}")
  public void deleteAdminAccount(@PathVariable UUID adminId) {
    adminAccountService.deleteAdminAccount(adminId);
  }

  @Operation(summary = "관리자 계정 로그인")
  @PostMapping("/login")
  public JwtSetResponse loginAdmin(@Valid @RequestBody LoginAdminAccountRequest request) {
    return adminAccountService.loginAdminAccount(request);
  }

  @Operation(summary = "관리자 계정 로그인 갱신")
  @PostMapping("/reissue")
  public JwtSetResponse refreshAdminAccount(
      @Valid @RequestBody ReissueAdminAccountRequest request) {
    return adminAccountService.reissueAdminAccount(request);
  }
}
