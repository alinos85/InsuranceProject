package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Message;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;

public class EmailClaimExpire extends EmailMecanism implements Message {

  private final Claim claim;
  private final Messageable user;

  public EmailClaimExpire(Claim claim, Messageable user) {
    this.claim = claim;
    this.user = user;
  }

  @Override
  public Messageable getMessageable() {
    return user;
  }

  @Override
  public String getBody() {

    String htmlString = mecanismEmail("templateExpireClaim.html")
        .replace("$claimNumber", claim.getId().value);

    return htmlString;
  }

  @Override
  public String getSubject() {
    return "Expire Claim";
  }
}
