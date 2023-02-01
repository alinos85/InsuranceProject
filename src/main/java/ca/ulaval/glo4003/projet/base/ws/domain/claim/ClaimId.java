package ca.ulaval.glo4003.projet.base.ws.domain.claim;

import java.util.UUID;

public class ClaimId {

  public String value;

  public ClaimId() {
    this.value = UUID.randomUUID().toString();
  }

  public ClaimId(String value) {
    this.value = UUID.fromString(value).toString();
  }

}
