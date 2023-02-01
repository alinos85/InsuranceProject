package ca.ulaval.glo4003.projet.base.ws.infrastructure.quote;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferNotFoundExeption;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class OfferRepositoryInMemoryTest {

  @Mock
  private OfferId offerId;

  private OfferId offerIdNull;

  @Mock
  private QuoteOffer quote1;

  private QuoteOfferRepository quoteOfferRepository;

  @Before
  public void setUp() {
    initMocks(this);
    offerIdNull = new OfferId();
    quoteOfferRepository = new OfferRepositoryInMemory();

    offerId = new OfferId(UUID.randomUUID().toString());

    willReturn(offerId).given(quote1).getOfferId();

    this.quoteOfferRepository.save(quote1);
  }

  @Test
  public void givenAnExistingId_whenFindingQuote_thenReturnQuote() {
    QuoteOffer returnedOfferQuote = quoteOfferRepository.findById(quote1.getOfferId());

    assertEquals(returnedOfferQuote, quote1);
  }

  @Test(expected = OfferNotFoundExeption.class)
  public void givenInvalidOfferID_whenFindingQuote_thenThrowOfferNotFoundException() {
    quoteOfferRepository.findById(offerIdNull);
  }
}

