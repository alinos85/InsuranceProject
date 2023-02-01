package ca.ulaval.glo4003.projet.base.ws.service.authentification.assembler;

import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class AuthentificationAssembler {

  private String hmacKey = "MyOneWayEncryptionKey";

  public AuthentificationToken create(User user) {
    String email = user.getId();
    Algorithm algorithm = Algorithm.HMAC256(hmacKey);
    String token = JWT.create()
        .withIssuer(email)
        .sign(algorithm);
    AuthentificationToken authentificationToken = new AuthentificationToken(token);
    return authentificationToken;
  }
}
