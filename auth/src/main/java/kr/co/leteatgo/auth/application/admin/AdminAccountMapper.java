package kr.co.leteatgo.auth.application.admin;

import kr.co.leteatgo.auth.application.admin.dto.RegisterAdminAccountRequest;
import kr.co.leteatgo.auth.domain.admin.AdminAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminAccountMapper {

  private final PasswordEncoder passwordEncoder;

  public AdminAccount create(RegisterAdminAccountRequest request) {
    String encodedPwd = passwordEncoder.encode(request.loginPwd());
    return AdminAccount.builder()
        .name(request.name())
        .loginId(request.loginId())
        .loginPwd(encodedPwd)
        .build();
  }
}
