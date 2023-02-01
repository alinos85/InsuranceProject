package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

public class Furniture extends ClaimValueDetails {

  private final String FURNITURE = "Meuble et électroménagers";

  public Furniture() {
  }

  @Override
  public String getName() {
    return this.FURNITURE;
  }
}
