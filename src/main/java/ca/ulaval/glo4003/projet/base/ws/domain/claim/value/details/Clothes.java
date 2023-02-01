package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

import java.math.BigDecimal;

public class Clothes extends ClaimValueDetails {

  private final String CLOTHES = "Vêtements";
  private BigDecimal amount;

  public Clothes() {
  }

  @Override
  public String getName() {
    return this.CLOTHES;
  }
}
