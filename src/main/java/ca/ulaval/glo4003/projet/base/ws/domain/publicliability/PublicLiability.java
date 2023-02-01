package ca.ulaval.glo4003.projet.base.ws.domain.publicliability;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.exception.InvalidPublicLiabilityAmountException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PublicLiability {

  public Money amount;

  public PublicLiability(BigDecimal amount) {
    if (amount.doubleValue() != 1000000 && amount.doubleValue() != 2000000) {
      throw new InvalidPublicLiabilityAmountException();
    }

    this.amount = new Money(amount);
  }

  public PublicLiability(double amount) {
    if (amount != 1000000 && amount != 2000000) {
      throw new InvalidPublicLiabilityAmountException();
    }

    this.amount = new Money(amount);
  }

  public boolean isEqual(BigDecimal amount) {
    BigDecimal liabilityPrice = this.amount.amount;
    boolean k = liabilityPrice.equals(amount.setScale(2, RoundingMode.HALF_UP));
    return k;
  }

  public Money getAmount() {
    return amount;
  }
}
