package kr.co.leteatgo.etc.application.storage;


import common.aws.s3.AwsS3Folder;
import common.aws.s3.PreSignedUrl;
import common.aws.s3.PreSignedUrls;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {

  PreSignedUrl createPreSignedUrl(AwsS3Folder folder);

  PreSignedUrls createPreSignedUrls(AwsS3Folder folder, Integer count);

  List<String> uploadImages(AwsS3Folder folder, List<MultipartFile> images);

  void deleteFiles(String fileName);
}
