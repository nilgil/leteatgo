package kr.co.leteatgo.geo.common;

import java.util.Optional;
import java.util.function.Function;

public class OptionalUtils {

	public static <T, R> R getValueOrDefault(T value, Function<T, R> function, R defaultValue) {
		return Optional.ofNullable(value).map(function).orElse(defaultValue);
	}
}
