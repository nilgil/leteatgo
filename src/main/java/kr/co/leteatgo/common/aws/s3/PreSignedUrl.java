package kr.co.leteatgo.common.aws.s3;

public record PreSignedUrl(String label, String imageUrl, String uploadUrl) {

	public PreSignedUrl labeling(String label) {
		return new PreSignedUrl(label, imageUrl, uploadUrl);
	}
}
