package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;

public abstract class ClaimValueDetails {

  private Money amount;

  public abstract String getName();

  public Money getAmount() {

    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }
}
