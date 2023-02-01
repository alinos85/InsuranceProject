package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;

public interface QuoteCalculator {

  Money calculateQuoteValue(Quote quote);
}
