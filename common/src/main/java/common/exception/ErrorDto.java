package common.exception;


public class ErrorDto {

  private final int status;
  private final String subCode;
  private final String message;
  private final String detail;

  public ErrorDto() {
    this(null);
  }

  public ErrorDto(String detail) {
    this(ErrorCode.UNKNOWN_SERVER_ERROR, detail);
  }

  public ErrorDto(ErrorCode subCode, String detail) {
    this(subCode.getStatus(), subCode.getSubCode(), subCode.getMessage(), detail);
  }

  public ErrorDto(int status, String subCode, String message, String detail) {
    this.status = status;
    this.subCode = subCode;
    this.message = message;
    this.detail = detail;
  }

  public int getStatus() {
    return status;
  }

  public String getSubCode() {
    return subCode;
  }

  public String getMessage() {
    return message;
  }

  public String getDetail() {
    return detail;
  }

  @Override
  public String toString() {
    return "ErrorDto{" +
        "status=" + status +
        ", subCode='" + subCode + '\'' +
        ", message='" + message + '\'' +
        ", detail='" + detail + '\'' +
        '}';
  }
}
