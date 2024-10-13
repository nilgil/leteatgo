package kr.co.leteatgo.store.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.store.application.FoodTypeService;
import kr.co.leteatgo.store.application.dto.FoodTypeResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "Food Type", description = "v1")
@RequiredArgsConstructor
@RequestMapping("/v1/food-types")
@RestController
public class FoodTypeControllerV1 {

	private final FoodTypeService foodTypeService;

	@GetMapping
	List<FoodTypeResponse> getFoodTypes() {
		return foodTypeService.getFoodTypes();
	}

	@GetMapping("/{foodType}/images")
	List<String> getFoodTypeImages(@PathVariable String foodType) {
		return foodTypeService.getFoodTypeImages(foodType);
	}

	@DeleteMapping("/cache")
	void evictFoodTypesCache() {
		foodTypeService.evictFoodTypesCache();
		foodTypeService.evictFoodTypeImagesCache();
	}
}
