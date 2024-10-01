package kr.co.leteatgo.auth.infrastructure.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretsManagerConfig {

  @Bean
  public AWSSecretsManager awsSecretsManager(
      @Value("${cloud.aws.region.static}") String region,
      @Value("${cloud.aws.credentials.access-key}") String accessKey,
      @Value("${cloud.aws.credentials.secret-key}") String secretKey
  ) {
    return AWSSecretsManagerClientBuilder
        .standard()
        .withRegion(region)
        .withCredentials(
            new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)
            )
        ).build();
  }
}
