package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class QuoteOfferCalculatorProxyTest {

  @Mock
  private ApiService apiService;

  @InjectMocks
  private CalculatorProxy calculatorProxy;

  @Before
  public void setUp() {
    initMocks(this);

  }

//  @Test
//  public void givenCalculatorProcess_whenExternCalculatorIsOffline_thenTakeOfflineCalculator() {
//    OfferCalculator quoteOfferCalculator = calculatorProxy.getQuoteOfferCalculator();
//    Assert.assertTrue(quoteOfferCalculator instanceof CalculatorApi);
//  }

}
