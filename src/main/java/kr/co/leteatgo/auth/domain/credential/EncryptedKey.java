package kr.co.leteatgo.auth.domain.credential;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class EncryptedKey {

	@Column(name = "encrypted_key")
	private String value;

	private EncryptedKey(String value) {
		if (isBlank(value)) {
			throw new IllegalArgumentException("cannot use blank value");
		}
		this.value = value;
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}

	public static EncryptedKey valueOf(String value) {
		return new EncryptedKey(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EncryptedKey that = (EncryptedKey)o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "EncryptedKey{" +
			"value='" + value + '\'' +
			'}';
	}
}
