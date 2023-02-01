package ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier;

import java.math.BigDecimal;

public class DecreasePriceModifierValue extends PriceModifierValue {

  public DecreasePriceModifierValue(BigDecimal value) {
    super(value);
  }

  @Override
  public BigDecimal apply(BigDecimal price) {
    return price.subtract(value);
  }
}
