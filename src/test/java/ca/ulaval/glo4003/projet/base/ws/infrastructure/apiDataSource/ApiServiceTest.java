package ca.ulaval.glo4003.projet.base.ws.infrastructure.apiDataSource;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculated;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiDataSource;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.MapperApiResponse;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.QuoteCalculatedAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteApiDto;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteCalculatedDTO;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

  @Mock
  private ApiDataSource mockApiDataSource;

  @Mock
  private MapperApiResponse mockMapperApiResponse;

  @Mock
  private QuoteCalculatedAssembler mockQuoteCalculatedAssembler;
  private String parseQuote;

  @Mock
  private QuoteCalculatedDTO quoteCalculatedDTO;

  @Mock
  private Quote quote;

  @Mock
  private QuoteApiDto quoteApiDto;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private Money money;

  @Mock
  private BigDecimal publicLiabilityAmount;

  private QuoteCalculated quoteCalculated;

  @InjectMocks
  private ApiService apiService;

  @Before
  public void setUp() {
    initMocks(this);

    willReturn(quoteCalculatedDTO).given(mockApiDataSource).callForQuoteOffer(quote);
    willReturn(quoteCalculatedDTO).given(mockApiDataSource).callForRenewal(insurancePolicy, money);
    willReturn(quoteCalculatedDTO).given(mockApiDataSource).callForUpdate(insurancePolicy, money, publicLiabilityAmount);
    willReturn(quoteCalculated).given(mockQuoteCalculatedAssembler).create(quoteCalculatedDTO);

  }

  @Test
  public void givenAResponseApiFromExternSource_whenAssemblyAnswerToDomainValue_thenHaveAQuoteCalculated() {
    QuoteCalculated quoteCalculated = apiService.calculatedQuoteOffer(quote);
    verify(mockQuoteCalculatedAssembler).create(quoteCalculatedDTO);
  }

  @Test
  public void givenAResponseApiFromExternSource_whenAssemblyAnswerToDomainValue_thenCallApiToGetQuoteCalculated() {
    QuoteCalculated quoteCalculated = apiService.calculatedQuoteOffer(quote);
    verify(mockApiDataSource).callForQuoteOffer(quote);
  }

  @Test
  public void givenAResponseApiFromExternSourceForRenw_whenAssemblyAnswerToDomainValue_thenCallApiToGetQuoteCalculated() {
    QuoteCalculated quoteCalculated = apiService.calculatedInsurancePolicyUpdate(insurancePolicy, money, publicLiabilityAmount);
    verify(mockApiDataSource).callForUpdate(insurancePolicy, money, publicLiabilityAmount);
  }

}
