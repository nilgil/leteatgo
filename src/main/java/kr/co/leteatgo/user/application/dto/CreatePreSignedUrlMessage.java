package kr.co.leteatgo.user.application.dto;

import java.util.List;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;

public record CreatePreSignedUrlMessage(
	AwsS3Folder folder,
	List<String> fileNames
) {

}
