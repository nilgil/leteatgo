package kr.co.leteatgo.auth.domain.credential;

public interface ApiKeyCredential {

  boolean matchApiKey(String apiKey);

  void updateApiKey(String apiKey);
}
