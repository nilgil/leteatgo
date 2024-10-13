package kr.co.leteatgo.store.application;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.leteatgo.common.exception.ErrorCode;
import kr.co.leteatgo.common.exception.LegException;
import kr.co.leteatgo.store.application.dto.FoodTypeResponse;
import kr.co.leteatgo.store.domain.foodtype.FoodType;
import kr.co.leteatgo.store.infrastructure.repository.FoodTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodTypeService {

	private final FoodTypeRepository foodTypeRepository;

	@Transactional(readOnly = true)
	@Cacheable(value = "foodTypes")
	public List<FoodTypeResponse> getFoodTypes() {
		return foodTypeRepository.getAllOrderBySort().stream()
			.map(FoodTypeResponse::new)
			.toList();
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "foodTypeImage", key = "#foodTypeName")
	public List<String> getFoodTypeImages(String foodTypeName) {
		FoodType foodType = foodTypeRepository.findByName(foodTypeName)
			.orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
		return foodType.getImages();
	}

	@CacheEvict(value = "foodTypes")
	public void evictFoodTypesCache() {
	}

	@CacheEvict(value = "foodTypeImage", allEntries = true)
	public void evictFoodTypeImagesCache() {
	}
}
