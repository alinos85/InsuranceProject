package ca.ulaval.glo4003.projet.base.ws.domain.priceModifier.responsibility;

import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.PriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.building.InsuranceResponsibilityModifier;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InsuranceResponsibilityModifierTest {

  InsuranceResponsibilityModifier insuranceResponsibilityModifier;
  BigDecimal price;

  @Before
  public void setUp() {
    insuranceResponsibilityModifier = new InsuranceResponsibilityModifier();
    price = new BigDecimal(100);
  }

  @Test
  public void given_whenCalculatedPrimeForInsurance_thenAdd25pourcentofValue() {
    PriceModifierValue priceValue = insuranceResponsibilityModifier.modifyPrice(price);
    Assert.assertEquals(priceValue.value, new BigDecimal(25.00).setScale(2, BigDecimal.ROUND_HALF_UP));
  }

}
