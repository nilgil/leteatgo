package kr.co.leteatgo.store.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Deprecated
@Tag(name = "Food Type", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/food-types")
@RestController
public class FoodTypeControllerV0 {

	private final FoodTypeControllerV1 foodTypeController;

	@GetMapping
	List<FoodTypeResponseV0> getFoodTypes() {
		return foodTypeController.getFoodTypes().stream()
			.map(response -> new FoodTypeResponseV0(
				response.foodType(), response.sort(), response.color(), response.icon()))
			.toList();
	}

	@GetMapping("/{foodType}/images")
	List<String> getFoodTypeImages(@PathVariable String foodType) {
		return foodTypeController.getFoodTypeImages(foodType);
	}

	private record FoodTypeResponseV0(String foodType, Integer sort, String color, String image) {

	}
}
