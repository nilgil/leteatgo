package kr.co.leteatgo.common.aws.s3;

import lombok.Getter;

@Getter
public enum AwsS3Folder {
	STORE_IMAGES("store/images"),
	STORE_LICENCE_IMAGES("store/licence-images"),
	STORE_MENU_IMAGES("store/menu-images"),
	STORE_FOOD_TYPE_ICONS("store/food-type-icons"),
	STORE_FOOD_TYPE_IMAGES("store/food-type-images"),
	STORE_REVIEW_IMAGES("store/review-images"),

	POST_IMAGES("etc/post-images"),
	NOTICE_IMAGES("etc/notice-images"),
	EVENT_IMAGES("etc/event-images"),
	TERM_IMAGES("etc/term-images"),

	USER_PROFILE_IMAGES("kr/co/leteatgo/user/profile-images"),

	BANK_ACCOUNT_IMAGES("bank/account-images");

	private final String path;

	AwsS3Folder(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return path;
	}
}
