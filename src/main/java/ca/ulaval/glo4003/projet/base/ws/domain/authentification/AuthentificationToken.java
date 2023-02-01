package ca.ulaval.glo4003.projet.base.ws.domain.authentification;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthentificationToken {

  private String authToken;

  public AuthentificationToken(String token) {
    this.authToken = token;
  }

  public static String decode(String token) {
    String email;
    try {
      DecodedJWT jwt = JWT.decode(token);
      email = jwt.getIssuer();
    } catch (Exception e) {
      throw new TokenInvalidException();
    }
    return email;
  }

  public String getTokenString() {
    return this.authToken;
  }

}
