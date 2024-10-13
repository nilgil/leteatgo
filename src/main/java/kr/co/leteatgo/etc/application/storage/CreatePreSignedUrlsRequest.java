package kr.co.leteatgo.etc.application.storage;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;

public record CreatePreSignedUrlsRequest(
	AwsS3Folder folder,
	Integer count
) {

}
