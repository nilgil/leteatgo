package kr.co.leteatgo.etc.application.post.dto;

import common.aws.s3.PreSignedUrlResponse;
import java.util.List;

public record WriteEventResponse(
    PreSignedUrlResponse bannerImage,
    List<PreSignedUrlResponse> contentImages
) {

}
