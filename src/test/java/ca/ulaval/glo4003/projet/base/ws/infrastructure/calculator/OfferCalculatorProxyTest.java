package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.UrlConnectionValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class OfferCalculatorProxyTest {

  @Mock
  private ApiService apiService;

  @Mock
  private UrlConnectionValidation urlConnectionValidation;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private Money money;

  @InjectMocks
  private CalculatorProxy calculatorProxy;

  @Before
  public void setUp() {
    initMocks(this);

  }

  @Test
  public void givenCalculatorProcess_whenExternCalculatorIsOnline_thenTakeCalculatorApi() {
    when(urlConnectionValidation.pingHost("https://ulaval-tenants-quote.herokuapp.com")).thenReturn(true);
    OfferCalculator offerCalculator = calculatorProxy.getQuoteOfferCalculator();
    Assert.assertTrue(offerCalculator instanceof CalculatorApi);
  }

  @Test
  public void givenCalculatorProcess_whenExternCalculatorIsOffline_thenTakeOfflineCalculator() {
    when(urlConnectionValidation.pingHost("https://ulaval-tenants-quote.herokuapp.com")).thenReturn(false);
    OfferCalculator offerCalculator = calculatorProxy.getQuoteOfferCalculator();
    Assert.assertTrue(offerCalculator instanceof CalculatorOffline);
  }

}
