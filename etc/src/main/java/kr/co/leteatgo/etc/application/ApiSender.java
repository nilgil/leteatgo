package kr.co.leteatgo.etc.application;

import common.aws.s3.AwsS3Folder;
import common.aws.s3.PreSignedUrls;
import kr.co.leteatgo.etc.application.storage.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiSender {

  private final AwsS3Service awsS3Service;

  public PreSignedUrls createPreSignedUrls(AwsS3Folder folder, Integer count) {
    return awsS3Service.createPreSignedUrls(folder, count);
  }
}
