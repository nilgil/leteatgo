package kr.co.leteatgo.etc.infrastructure.common.config;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import kr.co.leteatgo.etc.infrastructure.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaConfig implements AuditorAware<String> {

  private final HttpUtils httpUtils;

  @Nonnull
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof AnonymousAuthenticationToken) {
      return Optional.ofNullable(httpUtils.getUserIP())
          .map(ips -> "ANONYMOUS-" + ips.split(",")[0]);
    }
    return Optional.of(authentication.getCredentials() + "-" + authentication.getPrincipal());
  }

}
