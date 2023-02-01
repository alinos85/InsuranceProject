package ca.ulaval.glo4003.projet.base.ws.infrastructure.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferNotFoundExeption;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import java.util.HashMap;
import java.util.Map;

public class OfferRepositoryInMemory implements QuoteOfferRepository {

  private Map<String, QuoteOffer> quoteOffers = new HashMap<>();

  @Override
  public QuoteOffer findById(OfferId offerId) {
    QuoteOffer quoteOffer = quoteOffers.get(offerId.value);
    if (quoteOffer == null) {
      throw new OfferNotFoundExeption(offerId.value);
    }

    return quoteOffer;
  }

  @Override
  public QuoteOffer findById(String id) {
    OfferId offerId = new OfferId(id);
    QuoteOffer quoteOffer = quoteOffers.get(offerId.value);
    if (quoteOffer == null) {
      throw new OfferNotFoundExeption(offerId.value);
    }

    return quoteOffer;
  }

  @Override
  public void save(QuoteOffer quoteOffer) {
    quoteOffers.put(quoteOffer.getOfferId().value, quoteOffer);
  }

  @Override
  public void delete(OfferId offerId) {
    this.quoteOffers.remove(offerId.value);
  }

}
