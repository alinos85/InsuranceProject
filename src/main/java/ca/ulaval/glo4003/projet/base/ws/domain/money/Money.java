package ca.ulaval.glo4003.projet.base.ws.domain.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

  private final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
  public BigDecimal amount;

  public Money(BigDecimal amount) {
    this.amount = amount.setScale(2, ROUNDING_MODE);
  }

  public Money(double amount) {
    this.amount = BigDecimal.valueOf(amount).setScale(2, ROUNDING_MODE);
  }

  public void add(Money money) {
    this.amount = this.amount.add(money.amount);
  }

  public boolean isEqual(Money money) {
    return this.amount.compareTo(money.amount) == 0;
  }

  public boolean isSmaller(Money money) {
    return this.amount.compareTo(money.amount) == -1;
  }
}
