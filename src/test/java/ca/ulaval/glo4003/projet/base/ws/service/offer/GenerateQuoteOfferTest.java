package ca.ulaval.glo4003.projet.base.ws.service.offer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.QuoteAssembler;
import java.time.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GenerateQuoteOfferTest {

  GenerateQuoteOffer generateQuoteOffer;
  Clock clock = Clock.systemDefaultZone();

  @Mock
  private QuoteAssembler quoteAssembler;

  @Mock
  private OfferCalculator offerCalculator;

  @Mock
  private MessageService messageService;

  @Mock
  private QuoteOfferRepository quoteOfferRepository;

  @Mock
  private OfferAssembler offerAssembler;

  @Mock
  QuoteRequestDTO quoteRequestDTO;

  @Mock
  OfferResponseDTO offerResponseDTO;

  @Mock
  private Quote quote;

  @Mock
  private QuoteOffer quoteOffer;

  @Before
  public void setUp() {
    generateQuoteOffer = new GenerateQuoteOffer(quoteAssembler, offerCalculator, messageService, quoteOfferRepository, offerAssembler, clock);

    when(quoteAssembler.create(quoteRequestDTO)).thenReturn(quote);
    when(quote.makeOffer(offerCalculator, messageService, clock)).thenReturn(quoteOffer);
    when(offerAssembler.create(quoteOffer)).thenReturn(offerResponseDTO);

  }

  @Test
  public void givenQuoteRequestDTO_whenGeneratingQuoteOffer_thenGenerateQuoteOffer() {
    OfferResponseDTO generatedOfferResponseDTO = generateQuoteOffer.generateQuoteOffer(quoteRequestDTO);

    assertEquals(offerResponseDTO, generatedOfferResponseDTO);
  }

  @Test
  public void givenQuoteRequestDTO_whenGeneratingQuoteOffer_thenSaveQuoteOfferToRepository() {
    generateQuoteOffer.generateQuoteOffer(quoteRequestDTO);

    verify(quoteOfferRepository).save(quoteOffer);
  }
}