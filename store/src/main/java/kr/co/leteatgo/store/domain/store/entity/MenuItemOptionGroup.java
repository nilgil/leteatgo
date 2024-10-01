package kr.co.leteatgo.store.domain.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuItemOptionGroup extends BaseIdEntity implements Comparable<MenuItemOptionGroup> {

  private Integer sort;
  @ManyToOne
  private MenuItem item;
  @ManyToOne
  private MenuOptionGroup optionGroup;

  @Builder
  private MenuItemOptionGroup(Integer sort, MenuItem item, MenuOptionGroup optionGroup) {
    this.sort = sort;
    this.item = item;
    this.optionGroup = optionGroup;
  }

  @Override
  public int compareTo(MenuItemOptionGroup o) {
    return this.sort.compareTo(o.sort);
  }
}
