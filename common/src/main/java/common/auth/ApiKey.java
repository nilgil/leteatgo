package common.auth;

public class ApiKey {

  public static final String API_KEY_PREFIX = "LegAK ";

  private final String value;

  private ApiKey(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("cannot use blank value");
    }
    if (value.startsWith(API_KEY_PREFIX)) {
      value = value.substring(API_KEY_PREFIX.length());
    }
    this.value = value;
  }

  public static ApiKey valueOf(String value) {
    return new ApiKey(value);
  }

  public String value() {
    return this.value;
  }
}
