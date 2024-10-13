package kr.co.leteatgo.store.domain.foodtype;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.co.leteatgo.store.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FoodType extends BaseEntity {

	@Id
	private String name;
	private Integer sort;
	private String color;
	private String icon;

	@ElementCollection
	private List<String> images;
}
