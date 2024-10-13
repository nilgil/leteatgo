package kr.co.leteatgo.store.domain.store.entity;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.common.ImagePath;
import kr.co.leteatgo.store.domain.store.enums.ItemStatus;
import kr.co.leteatgo.store.domain.store.value.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuItem extends BaseIdEntity implements Comparable<MenuItem> {

	private String name;
	private String description;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "image"))
	private ImagePath image;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "price"))
	private Money price;
	private Integer sort;
	@Enumerated(EnumType.STRING)
	private ItemStatus status;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<MenuItemOptionGroup> itemOptionGroups;

	@ManyToOne
	private MenuGroup menuGroup;

	@Builder
	private MenuItem(String name, String description, ImagePath image, Money price, Integer sort,
		ItemStatus status, List<MenuItemOptionGroup> itemOptionGroups) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.sort = sort;
		this.status = status;
		this.itemOptionGroups = itemOptionGroups;
	}

	public void changeItemStatus(ItemStatus status) {
		this.status = status;
	}

	public void connectMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public void addMenuItemOptionGroup(MenuItemOptionGroup menuItemOptionGroup) {
		this.itemOptionGroups.add(menuItemOptionGroup);
	}

	@Override
	public int compareTo(MenuItem o) {
		return this.sort.compareTo(o.sort);
	}
}
