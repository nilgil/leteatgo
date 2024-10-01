package kr.co.leteatgo.auth.application.jwt;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.CreateSecretRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.amazonaws.services.secretsmanager.model.UpdateSecretRequest;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!local")
@Service
public class AwsSecretKeyManager implements SecretKeyManager {

  private final AWSSecretsManager awsSecretsManager;
  private final String secretName;

  private Key secretKey;

  public AwsSecretKeyManager(AWSSecretsManager awsSecretsManager,
      @Value("${jwt.secret-name}") String secretName) {
    this.awsSecretsManager = awsSecretsManager;
    this.secretName = secretName;
  }

  @PostConstruct
  void init() {
    String awsSecretKey = getAwsSecret();
    if (awsSecretKey == null) {
      secretKey = KeyUtil.createSecretKey();
      createAwsSecret();
      return;
    }
    secretKey = KeyUtil.decodeBase64(awsSecretKey);
  }

  @Override
  public Key getSecretKey() {
    return secretKey;
  }

  @Override
  public void changeSecretKey() {
    secretKey = KeyUtil.createSecretKey();
    updateAwsSecret();
  }

  private String getAwsSecret() {
    GetSecretValueRequest request = new GetSecretValueRequest()
        .withSecretId(secretName);
    try {
      return awsSecretsManager.getSecretValue(request).getSecretString();
    } catch (ResourceNotFoundException e) {
      return null;
    }
  }

  private void createAwsSecret() {
    CreateSecretRequest request = new CreateSecretRequest()
        .withName(secretName)
        .withSecretString(KeyUtil.encodeBase64(secretKey));
    awsSecretsManager.createSecret(request);
  }

  private void updateAwsSecret() {
    UpdateSecretRequest request = new UpdateSecretRequest()
        .withSecretId(secretName)
        .withSecretString(KeyUtil.encodeBase64(secretKey));
    awsSecretsManager.updateSecret(request);
  }
}
