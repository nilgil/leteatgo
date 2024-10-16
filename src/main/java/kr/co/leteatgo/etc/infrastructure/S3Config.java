package kr.co.leteatgo.etc.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Profile("!local")
@Configuration
public class S3Config {

	@Bean
	public AmazonS3 backupS3(
		@Value("${cloud.aws.region.static}") String region,
		@Value("${cloud.aws.credentials.access-key}") String accessKey,
		@Value("${cloud.aws.credentials.secret-key}") String secretKey
	) {
		return AmazonS3ClientBuilder.standard()
			.withRegion(region)
			.withCredentials(
				new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(accessKey, secretKey)
				)
			).build();
	}
}
