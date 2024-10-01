package common.auth.jwt;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import common.exception.ErrorCode;
import common.exception.LegException;
import java.text.ParseException;
import java.util.List;

public class JwtParser {

  public static final String CLAIM_ACCOUNT_TYPE = "ACCOUNT_TYPE";
  public static final String CLAIM_TOKEN_TYPE = "TOKEN_TYPE";
  public static final String CLAIM_ROLES = "ROLES";

  public static TokenInfo parse(JwtToken jwtToken) {
    try {
      return createTokenInfo(getJwtClaimsSet(parseJwt(jwtToken)));
    } catch (ParseException e) {
      throw new LegException(ErrorCode.BAD_CREDENTIAL, e);
    }
  }

  private static SignedJWT parseJwt(JwtToken jwtToken) throws ParseException {
    return SignedJWT.parse(jwtToken.value());
  }

  private static JWTClaimsSet getJwtClaimsSet(SignedJWT signedJWT) throws ParseException {
    return signedJWT.getJWTClaimsSet();
  }

  private static TokenInfo createTokenInfo(JWTClaimsSet jwtClaimsSet) throws ParseException {
    String identity = jwtClaimsSet.getSubject();
    String accountType;
    String tokenType;
    List<String> roles;
    try {
      accountType = jwtClaimsSet.getStringClaim(CLAIM_ACCOUNT_TYPE);
      tokenType = jwtClaimsSet.getStringClaim(CLAIM_TOKEN_TYPE);
      roles = jwtClaimsSet.getStringListClaim(CLAIM_ROLES);
    } catch (Exception e) {
      accountType = jwtClaimsSet.getStringClaim("accountType");
      tokenType = jwtClaimsSet.getStringClaim("tokenType");
      roles = jwtClaimsSet.getStringListClaim("roles");
    }
    return new TokenInfo(tokenType, accountType, identity, roles);
  }
}
