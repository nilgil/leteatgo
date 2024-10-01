package kr.co.leteatgo.user.application.dto;

import common.aws.s3.AwsS3Folder;
import java.util.List;

public record CreatePreSignedUrlMessage(
    AwsS3Folder folder,
    List<String> fileNames
) {


}
