package ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier;

import java.math.BigDecimal;

public abstract class PriceModifierValue {

  public BigDecimal value;

  public PriceModifierValue(BigDecimal value) {
    this.value = value;
  }

  public abstract BigDecimal apply(BigDecimal price);

}
