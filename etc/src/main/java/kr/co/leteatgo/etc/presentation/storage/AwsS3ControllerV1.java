package kr.co.leteatgo.etc.presentation.storage;


import common.aws.s3.PreSignedUrlRequest;
import common.aws.s3.PreSignedUrls;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.leteatgo.etc.application.storage.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Tag(name = "Aws S3", description = "Aws S3 관련 API")
@RestController
@RequestMapping("/v1/aws/s3")
@RequiredArgsConstructor
public class AwsS3ControllerV1 {

  private final AwsS3Service s3Service;

  @Operation(summary = "PreSignedUrl 여러 개 생성")
  @PostMapping("/pre-signed-urls")
  PreSignedUrls createPreSignedUrls(@RequestBody PreSignedUrlRequest request) {
    return s3Service.createPreSignedUrls(request.folder(), request.count());
  }
}
