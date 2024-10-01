package kr.co.leteatgo.auth.domain.credential;

public interface IdPwdCredential extends Credential {

  String loginId();

  String encryptedPwd();

  void updateLoginPwd(String encryptedPwd);
}
