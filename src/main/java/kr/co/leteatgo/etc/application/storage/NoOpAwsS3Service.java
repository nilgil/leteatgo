package kr.co.leteatgo.etc.application.storage;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;
import kr.co.leteatgo.common.aws.s3.PreSignedUrl;
import kr.co.leteatgo.common.aws.s3.PreSignedUrls;

@Component
public class NoOpAwsS3Service implements AwsS3Service {

	@Override
	public PreSignedUrl createPreSignedUrl(AwsS3Folder folder) {
		return null;
	}

	@Override
	public PreSignedUrls createPreSignedUrls(AwsS3Folder folder, Integer count) {
		return null;
	}

	@Override
	public List<String> uploadImages(AwsS3Folder folder, List<MultipartFile> images) {
		return List.of();
	}

	@Override
	public void deleteFiles(String fileName) {

	}
}
