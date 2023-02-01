package ca.ulaval.glo4003.projet.base.ws.domain.identity;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;

public class Identity implements Messageable {

  public String name;
  public String birthday;
  public String gender;
  public EmailAddress email;

  @Override
  public EmailAddress getEmailAddress() {
    return email;
  }
}
