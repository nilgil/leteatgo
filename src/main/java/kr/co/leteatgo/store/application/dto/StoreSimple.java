package kr.co.leteatgo.store.application.dto;

import kr.co.leteatgo.store.domain.store.enums.RewardRatio;

public record StoreSimple(
	String mainImage, String name, RewardRatio rewardRatio, Integer cookTime,
	Double startAvg, Long reviewCount, Long orderCount,
	String town, Integer distance, String event) {

}
