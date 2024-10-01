package common.aws.s3;

public record PreSignedUrlRequest(AwsS3Folder folder, Integer count) {

}
