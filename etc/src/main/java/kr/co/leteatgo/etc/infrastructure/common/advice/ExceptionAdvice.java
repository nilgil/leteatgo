package kr.co.leteatgo.etc.infrastructure.common.advice;

import common.exception.ErrorCode;
import common.exception.ErrorDto;
import common.exception.LegException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(value = LegException.class)
  public ResponseEntity<ErrorDto> handleLegException(LegException le) {
    ErrorDto error = new ErrorDto(le.errorCode(), le.detail());
    log.error("LegException : {}", error);
    return ResponseEntity.status(le.status()).body(error);
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException iae) {
    return handleLegException(new LegException(ErrorCode.BAD_REQUEST, iae));
  }

  @ExceptionHandler(value = AccessDeniedException.class)
  public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException tme) {
    return handleLegException(new LegException(ErrorCode.NO_AUTHORITY_RESOURCE, tme));
  }

  @ExceptionHandler(value = TypeMismatchException.class)
  public ResponseEntity<ErrorDto> handleTypeMismatchException(TypeMismatchException tme) {
    return handleLegException(new LegException(ErrorCode.BAD_REQUEST, tme));
  }

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException hmnre) {
    return handleLegException(new LegException(ErrorCode.BAD_REQUEST, hmnre));
  }

  @ExceptionHandler(value = WebClientResponseException.class)
  public ResponseEntity<ErrorDto> handleWebClientResponseException(
      WebClientResponseException wcre) {
    try {
      LegException legException = new LegException(
          ErrorCode.findByStatus(wcre.getStatusCode().value()), wcre);
      return handleLegException(legException);
    } catch (Exception e) {
      return handleLegException(new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, wcre));
    }
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception e) {
    return handleLegException(new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, e));
  }
}
