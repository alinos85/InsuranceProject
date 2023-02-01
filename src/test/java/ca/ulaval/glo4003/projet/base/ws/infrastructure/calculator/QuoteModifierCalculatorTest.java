package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.DecreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.IncreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculatedModifyBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuoteModifierCalculatorTest extends QuoteCalculatedModifyBuilder {

  public QuoteModifierCalculatorTest() {
    super();
  }

  private QuoteModifierCalculatorTest quoteCalculatedModifyBuilder;
  private IncreasePriceModifierValue increasePriceModifierValue;
  private DecreasePriceModifierValue decreasePriceModifierValue;
  private BigDecimal value = new BigDecimal(50);
  private BigDecimal price = new BigDecimal(100);

  @Before
  public void setUp() {
    quoteCalculatedModifyBuilder = new QuoteModifierCalculatorTest();
  }

  @Test
  public void givenIncreaseModifier_whenApplyPrice_thenIncreaseAmount() {
    increasePriceModifierValue = new IncreasePriceModifierValue(value);
    quoteCalculatedModifyBuilder.addPriceModifier(increasePriceModifierValue);
    Money money = quoteCalculatedModifyBuilder.applyPriceModifiers(price);
    Assert.assertEquals(money.amount, new BigDecimal(150).setScale(2, RoundingMode.HALF_UP));
  }

  @Test
  public void givenDecreaseModifer_whenApplyPrice_thenDecreaseAmount() {
    decreasePriceModifierValue = new DecreasePriceModifierValue(value);
    quoteCalculatedModifyBuilder.addPriceModifier(decreasePriceModifierValue);
    Money money = quoteCalculatedModifyBuilder.applyPriceModifiers(price);
    Assert.assertEquals(money.amount, new BigDecimal(50).setScale(2, RoundingMode.HALF_UP));
  }

}
