package kr.co.leteatgo.etc.infrastructure.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.exception.ErrorCode;
import common.exception.ErrorDto;
import common.exception.LegException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull FilterChain filterChain
  ) {

    try {
      filterChain.doFilter(request, response);
    } catch (WebClientResponseException wcre) {
      ErrorDto error = wcre.getResponseBodyAs(ErrorDto.class);
      logError(error, wcre);
      sendError(error, response);
    } catch (Exception e) {
      Optional.ofNullable(e.getCause())
          .filter(cause -> cause instanceof LegException)
          .map(cause -> (LegException) cause)
          .ifPresentOrElse(le -> {
            ErrorDto error = new ErrorDto(le.errorCode(), le.detail());
            logError(error, le);
            sendError(error, response);
          }, () -> {
            ErrorDto error = new ErrorDto(ErrorCode.UNKNOWN_SERVER_ERROR, e.getMessage());
            logError(error, e);
            sendError(error, response);
          });
    }
  }

  private void logError(ErrorDto error, Exception e) {
    log.error("{} : {}", e.getCause().getClass().getName(), error);
    log.error(e.toString());
  }

  private void sendError(ErrorDto errorDto, HttpServletResponse response) {
    try {
      String jsonBody = objectMapper.writeValueAsString(errorDto);
      response.setStatus(errorDto.getStatus());
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write(jsonBody);
    } catch (IOException ex) {
      throw new RuntimeException("unknown error");
    }
  }
}
