package ca.ulaval.glo4003.projet.base.ws.domain.actuary;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;

public class Actuary implements User {

  private String hashedPassword;
  private EmailAddress emailAddress;

  public Actuary(EmailAddress emailAddress, String hashedPassword) {
    this.hashedPassword = hashedPassword;
    this.emailAddress = emailAddress;
  }

  @Override
  public String getId() {
    return this.emailAddress.value;
  }

  @Override
  public String getHashedPassword() {
    return this.hashedPassword;
  }

}
