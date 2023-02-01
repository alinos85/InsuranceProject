package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

public class Electronics extends ClaimValueDetails {

  private String ELECTRONICS = "Électronique";

  public Electronics() {
  }

  @Override
  public String getName() {
    return this.ELECTRONICS;
  }
}
