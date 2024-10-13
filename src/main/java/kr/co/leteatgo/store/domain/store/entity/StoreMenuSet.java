package kr.co.leteatgo.store.domain.store.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreMenuSet extends BaseIdEntity {

	private String name;
	@OneToMany(mappedBy = "menuSet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MenuGroup> groups;

	@Builder
	private StoreMenuSet(String name, List<MenuGroup> groups) {
		this.name = name;
		this.groups = groups;
	}

	public static StoreMenuSet init() {
		return new StoreMenuSet("", null);
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void addMenuGroup(MenuGroup menuGroup) {
		menuGroup.connectMenuSet(this);
		this.groups.add(menuGroup);
	}
}
