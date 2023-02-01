package ca.ulaval.glo4003.projet.base.ws.service.quote;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.InformationAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.InformationsDTO;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.quote.OfferRepositoryInMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class GetQuoteInformationTest {

  private String QUOTE_ID = "QUOTE_ID";
  private Quote quote;

  @Mock
  private InformationAssembler informationAssembler;

  @Mock
  private QuoteOffer quoteOffer;

  private OfferCalculator offerCalculator;

  private MessageService messageService;
  @Mock
  private InformationsDTO informationsDTO;

  private GetQuoteInformation getQuoteInformation;

  @Mock
  private OfferRepositoryInMemory quoteOfferRepository;

  private java.time.Clock Clock;

  @Before
  public void setUp() {
    initMocks(this);
    getQuoteInformation = new GetQuoteInformation(quoteOfferRepository, informationAssembler, offerCalculator, messageService, Clock);

    quote = mock(Quote.class);
    informationsDTO = mock(InformationsDTO.class);

    willReturn(quoteOffer).given(quote).makeOffer(offerCalculator, messageService, Clock);
    willReturn(informationsDTO).given(informationAssembler).create(quote, quoteOffer);
    willReturn(quote).given(quoteOffer).getQuote();
    willReturn(quoteOffer).given(quoteOfferRepository).findById(QUOTE_ID);
  }

  @Test
  public void givenAQuoteDTO_whenAddingQuote_thenShouldCreateAndSaveQuote() {
    InformationsDTO information = getQuoteInformation.findQuote(QUOTE_ID);

    Assert.assertEquals(information, informationsDTO);
    assertThat(informationsDTO, instanceOf(InformationsDTO.class));
  }
}