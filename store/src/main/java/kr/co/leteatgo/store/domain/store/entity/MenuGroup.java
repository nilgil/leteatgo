package kr.co.leteatgo.store.domain.store.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuGroup extends BaseIdEntity implements Comparable<MenuGroup> {

  private String name;
  private Integer sort;
  @OneToMany(mappedBy = "menuGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MenuItem> items;
  @ManyToOne
  private StoreMenuSet menuSet;

  @Builder
  private MenuGroup(String name, Integer sort, List<MenuItem> items) {
    this.name = name;
    this.sort = sort;
    this.items = items;
  }

  public void connectMenuSet(StoreMenuSet menuSet) {
    this.menuSet = menuSet;
  }

  public void addMenuItem(MenuItem menuItem) {
    menuItem.connectMenuGroup(this);
    this.items.add(menuItem);
  }

  @Override
  public int compareTo(MenuGroup o) {
    return this.sort.compareTo(o.sort);
  }
}
