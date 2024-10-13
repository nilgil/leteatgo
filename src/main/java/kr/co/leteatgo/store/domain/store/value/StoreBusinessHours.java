package kr.co.leteatgo.store.domain.store.value;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StoreBusinessHours {

	private String dayOff;

	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<BusinessHour> businessHours;

}
