package kr.co.leteatgo.common.auth.jwt;

public class JwtToken {

	public static final String BEARER_PREFIX = "Bearer ";

	private final String value;

	private JwtToken(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("is blank value");
		}
		if (value.startsWith(BEARER_PREFIX)) {
			value = value.substring(BEARER_PREFIX.length());
		}
		this.value = value;
	}

	public static JwtToken valueOf(String value) {
		return new JwtToken(value);
	}

	public String value() {
		return this.value;
	}

	@Override
	public String toString() {
		return "JwtToken{" +
			"\n\t\tvalue='" + value + '\'' +
			"\n\t}";
	}
}
