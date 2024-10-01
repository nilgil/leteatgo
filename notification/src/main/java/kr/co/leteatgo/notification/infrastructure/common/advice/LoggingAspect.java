package kr.co.leteatgo.notification.infrastructure.common.advice;


import java.util.Optional;
import kr.co.leteatgo.notification.infrastructure.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

  private final HttpUtils httpUtils;

  @Pointcut("execution(* kr.co.leteatgo..*Controller*.*(..))")
  public void controllerPointcut() {
  }

  @Before("controllerPointcut()")
  public void beforeController(JoinPoint jp) {
    String type = "GUEST";
    String id = "";
    Optional<Authentication> authenticationOpt = Optional.ofNullable(
        SecurityContextHolder.getContext().getAuthentication());
    if (authenticationOpt.isPresent()) {
      Authentication authentication = authenticationOpt.get();
      type = authentication.getCredentials().toString();
      id = authentication.getPrincipal().toString();
    }
    log.info("[IP] {}, [Type] {}, [ID] {}, [Method] {}, [URI] {}, [Call] {}, [ARGS] {}",
        httpUtils.getUserIP(), type, id, httpUtils.getHttpMethod(),
        httpUtils.getRequestURI(), jp.getSignature().toShortString(), jp.getArgs());
  }

  @Profile({"local", "dev"})
  @Around("controllerPointcut()")
  public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
    String method = pjp.getSignature().toShortString();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object obj = pjp.proceed();
    stopWatch.stop();
    log.info("[Method] {} , [TIMER] : {}ms", method, stopWatch.getTotalTimeMillis());
    return obj;
  }
}
