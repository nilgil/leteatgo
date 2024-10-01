package kr.co.leteatgo.etc.application.storage;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import common.aws.s3.AwsS3Folder;
import common.aws.s3.PreSignedUrl;
import common.aws.s3.PreSignedUrls;
import common.exception.ErrorCode;
import common.exception.LegException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {

  private final AmazonS3 amazonS3;

  @Value("${cloud.aws.s3.bucket}")
  private String AWS_S3_BUCKET;
  @Value("${cloud.aws.s3.image-host}")
  private String AWS_IMAGE_HOST;

  @Override
  public PreSignedUrl createPreSignedUrl(AwsS3Folder folder) {
    String randomId = UUID.randomUUID().toString();
    String imageUrl = String.format("%s/%s/%s", AWS_IMAGE_HOST, folder, randomId);
    
    GeneratePresignedUrlRequest request = generatePsuRequest(AWS_S3_BUCKET, folder, randomId);
    URL presignedUrl = amazonS3.generatePresignedUrl(request);

    return new PreSignedUrl(null, imageUrl, presignedUrl.toString());
  }

  @Override
  public PreSignedUrls createPreSignedUrls(AwsS3Folder folder, Integer count) {
    List<PreSignedUrl> results = IntStream.range(0, count)
        .mapToObj(i -> CompletableFuture.supplyAsync(() -> createPreSignedUrl(folder)))
        .map(CompletableFuture::join)
        .toList();
    if (results.size() != count) {
      throw new LegException(ErrorCode.INTERNAL_SERVER_ERROR, "fail to create pre-signed url");
    }
    return new PreSignedUrls(results);
  }

  private GeneratePresignedUrlRequest generatePsuRequest(String bucket, AwsS3Folder folder,
      String fileName) {

    String key = folder + "/" + fileName;

    GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key)
        .withMethod(HttpMethod.PUT)
        .withExpiration(getPreSignedUrlExpiration());

    request.addRequestParameter(
        Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());

    return request;
  }

  private Date getPreSignedUrlExpiration() {
    Date expiration = new Date();

    long expTimeMillis = expiration.getTime();
    expTimeMillis += 1000 * 60 * 2; // 2m

    expiration.setTime(expTimeMillis);
    return expiration;
  }

  @Override
  public List<String> uploadImages(AwsS3Folder folder, List<MultipartFile> images) {
    if (images == null || images.isEmpty()) {
      return new ArrayList<>();
    }
    List<String> imageUrls = new ArrayList<>();
    // 순서 보장
    images.forEach(image -> {
      String imageName = makeImageName(image.getOriginalFilename());
      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentLength(image.getSize());
      objectMetadata.setContentType(image.getContentType());

      try (InputStream inputStream = image.getInputStream()) {
        amazonS3.putObject(new PutObjectRequest(
            AWS_S3_BUCKET, folder + "/" + imageName, inputStream, objectMetadata
        ).withCannedAcl(CannedAccessControlList.PublicRead));
      } catch (IOException e) {
        throw new LegException(ErrorCode.UNKNOWN_SERVER_ERROR, "파일 업로드에 실패했습니다.");
      }
      imageUrls.add(imageName);
    });
    return imageUrls;
  }

  private String makeImageName(String fileName) {
    return UUID.randomUUID() + getExtension(fileName);
  }

  private String getExtension(String fileName) {
    try {
      return fileName.substring(fileName.lastIndexOf("."));
    } catch (StringIndexOutOfBoundsException e) {
      throw new LegException(ErrorCode.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
    }
  }

  @Override
  public void deleteFiles(String fileName) {
    amazonS3.deleteObject(new DeleteObjectRequest(AWS_S3_BUCKET, fileName));
  }
}
