package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.store.enums.ItemStatus;
import kr.co.leteatgo.store.domain.store.value.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuOptionItem extends BaseIdEntity implements Comparable<MenuOptionItem> {

	private String name;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "price"))
	private Money price;
	private ItemStatus status;
	private Integer sort;
	@ManyToOne
	private MenuOptionGroup optionGroup;

	@Builder
	private MenuOptionItem(String name, Money price, ItemStatus status, Integer sort,
		MenuOptionGroup optionGroup) {
		this.name = name;
		this.price = price;
		this.status = status;
		this.sort = sort;
		this.optionGroup = optionGroup;
	}

	public void connectMenuOptionGroup(MenuOptionGroup menuOptionGroup) {
		this.optionGroup = menuOptionGroup;
	}

	@Override
	public int compareTo(MenuOptionItem o) {
		return this.sort.compareTo(o.sort);
	}
}
