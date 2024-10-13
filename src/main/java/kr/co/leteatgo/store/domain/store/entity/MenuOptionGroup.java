package kr.co.leteatgo.store.domain.store.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.store.value.ChoiceLimit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuOptionGroup extends BaseIdEntity {

	private String name;
	private ChoiceLimit limit;
	private Integer sort;
	@OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuOptionItem> optionItems;

	@ManyToOne
	private Store store;

	@Builder
	private MenuOptionGroup(String name, ChoiceLimit limit, Integer sort,
		List<MenuOptionItem> optionItems, Store store) {
		this.name = name;
		this.limit = limit;
		this.sort = sort;
		this.optionItems = optionItems;
		this.store = store;
	}

	public void addMenuOptionItem(MenuOptionItem optionItem) {
		optionItem.connectMenuOptionGroup(this);
		this.optionItems.add(optionItem);
	}
}
