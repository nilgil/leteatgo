package kr.co.leteatgo.store.domain.store.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreFixSuggests {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<FixSuggest> fixSuggests;
}
