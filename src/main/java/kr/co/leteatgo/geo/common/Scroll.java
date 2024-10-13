package kr.co.leteatgo.geo.common;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.util.Streamable;

import jakarta.annotation.Nonnull;

public record Scroll<T>(List<T> content, Boolean hasNext) implements Streamable<T> {

	@Nonnull
	public <U> Scroll<U> map(@Nonnull Function<? super T, ? extends U> converter) {
		List<U> converted = this.stream()
			.map(converter)
			.collect(Collectors.toList());
		return new Scroll<>(converted, hasNext);
	}

	@Nonnull
	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}
}
