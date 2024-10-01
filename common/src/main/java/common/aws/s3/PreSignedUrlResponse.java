package common.aws.s3;


public class PreSignedUrlResponse {

  private final String label;
  private final String imageUrl;
  private final String uploadUrl;

  private PreSignedUrlResponse(PreSignedUrl preSignedUrl) {
    this.label = preSignedUrl.label();
    this.imageUrl = preSignedUrl.imageUrl();
    this.uploadUrl = preSignedUrl.uploadUrl();
  }

  public static PreSignedUrlResponse from(PreSignedUrl preSignedUrl) {
    return new PreSignedUrlResponse(preSignedUrl);
  }

  public String getLabel() {
    return label;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getUploadUrl() {
    return uploadUrl;
  }
}
