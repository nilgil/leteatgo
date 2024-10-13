package kr.co.leteatgo.etc.application.storage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;
import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.common.aws.s3.PreSignedUrls;

public interface AwsS3Service {

	PreSignedUrl createPreSignedUrl(AwsS3Folder folder);

	PreSignedUrls createPreSignedUrls(AwsS3Folder folder, Integer count);

	List<String> uploadImages(AwsS3Folder folder, List<MultipartFile> images);

	void deleteFiles(String fileName);
}
