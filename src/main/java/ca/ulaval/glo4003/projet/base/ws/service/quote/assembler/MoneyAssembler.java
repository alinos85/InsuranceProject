package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import java.math.BigDecimal;

public class MoneyAssembler {

  public BigDecimal create(Money money) {
    return money.amount;
  }

  public Money create(BigDecimal amount) {
    return new Money(amount);
  }
}
