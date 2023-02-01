package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;

public class UpdateInsurancePolicyCoverage {

  private Money publicLiabilityAmount;
  private Money insuranceAmount;

  public UpdateInsurancePolicyCoverage() {
    this.publicLiabilityAmount = null;
    this.insuranceAmount = null;
  }

  public Money getPublicLiabilityAmount() {
    return publicLiabilityAmount;
  }

  public void setPublicLiabilityAmount(Money publicLiabilityAmount) {
    this.publicLiabilityAmount = publicLiabilityAmount;
  }

  public Money getInsuranceAmount() {
    return insuranceAmount;
  }

  public void setInsuranceAmount(Money insuranceAmount) {
    this.insuranceAmount = insuranceAmount;
  }
}
