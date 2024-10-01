package common.exception;


public class LegException extends RuntimeException {

  private final ErrorCode errorCode;
  private final String detail;

  public LegException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.detail = null;
  }

  public LegException(ErrorCode errorCode, Throwable cause) {
    this(errorCode, cause.getMessage(), cause);
  }

  public LegException(ErrorCode errorCode, String detail) {
    this(errorCode, detail, null);
  }

  public LegException(ErrorCode errorCode, String detail, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
    this.detail = detail;
  }

  public ErrorCode errorCode() {
    return errorCode;
  }

  public int status() {
    return errorCode.getStatus();
  }

  public String message() {
    return errorCode.getMessage();
  }

  public String detail() {
    return detail;
  }
}
