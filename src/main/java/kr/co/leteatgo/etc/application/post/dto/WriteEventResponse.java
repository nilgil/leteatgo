package kr.co.leteatgo.etc.application.post.dto;

import java.util.List;

import kr.co.leteatgo.common.aws.s3.PreSignedUrlResponse;

public record WriteEventResponse(
	PreSignedUrlResponse bannerImage,
	List<PreSignedUrlResponse> contentImages
) {

}
