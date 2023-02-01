package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;

public interface QuoteOfferRepository {

  QuoteOffer findById(OfferId offerId);

  QuoteOffer findById(String offerId);

  void save(QuoteOffer quote);

  void delete(OfferId offerId);
}
