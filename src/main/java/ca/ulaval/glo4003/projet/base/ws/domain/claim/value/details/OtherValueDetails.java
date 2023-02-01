package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

public class OtherValueDetails extends ClaimValueDetails {

  private final String OTHER_VALUE_DETAILS = "Autres biens";

  public OtherValueDetails() {
  }

  @Override
  public String getName() {
    return this.OTHER_VALUE_DETAILS;
  }
}
