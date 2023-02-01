package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.DecreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.IncreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.PriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.PublicLiability;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculated;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class OfferCalculatorOfflineTest {

  @Mock
  Quote quote;
  private PriceModifierValue priceModifierValueDiscount;
  private PriceModifierValue priceModifierValueInscrease;
  private BigDecimal value;
  private QuoteCalculated quoteCalculated;
  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private InsurancePolicy insurancePolicy1m;

  private PublicLiability publicLiability = new PublicLiability(2000000);
  private PublicLiability publicLiability1m = new PublicLiability(1000000);

  private Money money = new Money(100);

  @InjectMocks
  private CalculatorOffline calculatorOffline;

  @Before
  public void setUp() {
    initMocks(this);
    priceModifierValueDiscount = new DecreasePriceModifierValue(new BigDecimal(10));
    priceModifierValueInscrease = new IncreasePriceModifierValue(new BigDecimal(50));
    value = new BigDecimal(100);
    quoteCalculated = new QuoteCalculated(value);
    when(insurancePolicy.getPublicLiability()).thenReturn(publicLiability);
    when(insurancePolicy1m.getPublicLiability()).thenReturn(publicLiability1m);
  }

  @Test
  public void givenQuotedCalculated_whenPriceModifierMustBeApplied_thenReturnPriceWithModifyApply() {
    calculatorOffline.addPriceModifier(priceModifierValueDiscount);
    calculatorOffline.addPriceModifier(priceModifierValueInscrease);
    Money money = calculatorOffline.applyPriceModifiers(value);
    QuoteOffer priceModify = new QuoteOffer(quote, money, Clock.systemDefaultZone());
    Assert.assertEquals(priceModify.getValue().amount, new BigDecimal(140.00).setScale(2, RoundingMode.HALF_UP));
  }

  @Test
  public void givenDiscountAndIncreasePriceModifier_whenCalculatedFinalPrice_thenOperationAreMade() {
    calculatorOffline.addPriceModifier(priceModifierValueDiscount);
    calculatorOffline.addPriceModifier(priceModifierValueInscrease);
    BigDecimal t = calculatorOffline.applyModifierValue(quoteCalculated.getPrice());
    Assert.assertEquals(t, new BigDecimal(140));
  }

  @Test
  public void givenOfflineCalculator_whenCalculateRenewalInsurancePolicyofTwoMillions_thenGetExpectedPrice() {

    RenewInsurancePolicyOffer renewInsurancePolicyOffer = calculatorOffline.calculatedRenewalInsurancePolicy(insurancePolicy, money);
    Assert.assertEquals(renewInsurancePolicyOffer.getValue().amount, new BigDecimal(125).setScale(2, RoundingMode.HALF_UP));

  }

  @Test
  public void givenOfflineCalculator_whenCalculateRenewalInsurancePolicyofOneMillions_thenGetExpectedPrice() {

    RenewInsurancePolicyOffer renewInsurancePolicyOffer = calculatorOffline.calculatedRenewalInsurancePolicy(insurancePolicy1m, money);
    Assert.assertEquals(renewInsurancePolicyOffer.getValue().amount, new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));

  }

}
