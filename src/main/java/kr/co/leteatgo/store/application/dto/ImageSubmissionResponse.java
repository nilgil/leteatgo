package kr.co.leteatgo.store.application.dto;

import java.util.List;
import java.util.UUID;

public record ImageSubmissionResponse(
	UUID storeId,
	List<String> images
) {

}
