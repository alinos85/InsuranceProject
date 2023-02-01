package ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier;

import java.math.BigDecimal;

public class IncreasePriceModifierValue extends PriceModifierValue {

  public IncreasePriceModifierValue(BigDecimal value) {
    super(value);
  }

  @Override
  public BigDecimal apply(BigDecimal price) {
    return price.add(value);
  }

}
