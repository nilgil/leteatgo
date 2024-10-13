package kr.co.leteatgo.store.domain.store;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import kr.co.leteatgo.store.domain.common.BaseIdEntity;
import kr.co.leteatgo.store.domain.common.ImagePath;
import kr.co.leteatgo.store.domain.store.entity.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreImageSubmission extends BaseIdEntity {

	@OrderColumn
	@ElementCollection
	@AttributeOverride(name = "value", column = @Column(name = "image"))
	private List<ImagePath> images;
	@OneToOne
	private Store store;

	public StoreImageSubmission(Store store, List<ImagePath> images) {
		this.store = store;
		this.images = images;
	}
}
