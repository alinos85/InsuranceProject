package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.DecreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.IncreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.PriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculated;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OfferCalculatorApiTest {

  @Mock
  Quote mockQuote;
  List<Animal> mockAnimals = new ArrayList<>();
  List<PriceModifierValue> modifierValues = new ArrayList<>();
  BigDecimal price = new BigDecimal(100);
  BigDecimal modifyPrice = new BigDecimal(110);
  @Mock
  Money value;
  @Mock
  InsurancePolicy insurancePolicy;
  @Mock
  Animal mockAnimal;
  @Mock
  QuoteOffer quoteOffer;
  @Mock
  RenewInsurancePolicyOffer renewInsurancePolicyOffer;
  @Mock
  private ApiService mockApiAssembler;
  @Mock
  private Quote quote;
  @Mock
  private QuoteCalculated quoteCalculated;
  @Mock
  private IncreasePriceModifierValue mockIncreasePriceModifierValue;
  @Mock
  private DecreasePriceModifierValue mockDecreasePriceModifierValue;
  @InjectMocks
  private CalculatorApi calculatorAPI;

  @Before
  public void setUp() {
    initMocks(this);

    mockAnimals.add(mockAnimal);
    willReturn(mockAnimals).given(mockQuote).getAnimals();

    willReturn(value).given(insurancePolicy).getInsuranceAmount();
    willReturn(quoteCalculated).given(mockApiAssembler).calculatedQuoteOffer(any(Quote.class));
    willReturn(quoteCalculated).given(mockApiAssembler).calculatedInsurancePolicyRenewal(insurancePolicy, value);
    willReturn(price).given(quoteCalculated).getPrice();
    willReturn(quoteCalculated).given(mockApiAssembler).calculatedInsurancePolicyUpdate(insurancePolicy, value, price);

  }

  @Test
  public void GivenCreationOfQuote_WhenQuoteHasAllItems_ThenCalculatePriceOfQuote() {
    when(mockApiAssembler.calculatedQuoteOffer(any(Quote.class))).thenReturn(quoteCalculated);

    assertEquals(calculatorAPI.calculateQuoteOffer(quote, Clock.systemDefaultZone()).getValue().amount,
        quoteCalculated.getPrice().setScale(2, RoundingMode.HALF_UP));
    verify(mockApiAssembler).calculatedQuoteOffer(quote);
  }

  @Test
  public void givenCalculToRenewInsurance_whenRenewIt_thenCalculateNewPrice() {
    assertEquals(calculatorAPI.calculatedRenewalInsurancePolicy(insurancePolicy, value).getValue().amount,
        quoteCalculated.getPrice().setScale(2, RoundingMode.HALF_UP));
    verify(mockApiAssembler).calculatedInsurancePolicyRenewal(insurancePolicy, value);
  }

  @Test
  public void givenCalculToUpdateInsurance_whenUpsdateIt_thenCalculateNewPrice() {
    assertEquals(calculatorAPI.calculatedUpdateInsurancePolicy(insurancePolicy, value, price).getValue().amount,
        quoteCalculated.getPrice().setScale(2, RoundingMode.HALF_UP));
    verify(mockApiAssembler).calculatedInsurancePolicyUpdate(insurancePolicy, value, price);
  }

}


