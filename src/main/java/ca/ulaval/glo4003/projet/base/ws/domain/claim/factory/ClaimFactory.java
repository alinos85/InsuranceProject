package ca.ulaval.glo4003.projet.base.ws.domain.claim.factory;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimFire;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimThievery;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimType;

public class ClaimFactory {

  public static Claim buildClaim(String type) {
    if (ClaimType.FIRE.toString().equals(type)) {
      return new ClaimFire();
    } else if (ClaimType.THIEVERY.toString().equals(type)) {
      return new ClaimThievery();
    } else {
      throw new ClaimTypeApiException(type);
    }
  }
}
