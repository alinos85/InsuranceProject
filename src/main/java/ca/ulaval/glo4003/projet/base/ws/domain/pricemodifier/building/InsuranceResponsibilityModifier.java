package ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.building;

import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.IPriceModify;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.IncreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.PriceModifierValue;
import java.math.BigDecimal;

public class InsuranceResponsibilityModifier implements IPriceModify {

  private static InsuranceResponsibilityModifier insuranceResponsibilityModifier;

  public static IPriceModify getInstance() {
    if (insuranceResponsibilityModifier == null) {
      insuranceResponsibilityModifier = new InsuranceResponsibilityModifier();
    }

    return insuranceResponsibilityModifier;
  }

  @Override
  public PriceModifierValue modifyPrice(BigDecimal price) {
    return new IncreasePriceModifierValue((price.multiply(new BigDecimal(0.25))
        .setScale(2, BigDecimal.ROUND_HALF_UP)));
  }
}
