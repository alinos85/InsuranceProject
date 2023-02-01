package ca.ulaval.glo4003.projet.base.ws.infrastructure.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jersey.repackaged.com.google.common.collect.Lists;

public class QuoteRepositoryInMemory implements QuoteRepository {

  private Map<String, Quote> quotes = new HashMap<>();

  @Override
  public List<Quote> findAll() {
    return Lists.newArrayList(quotes.values());
  }

  @Override
  public Quote findById(String id) {
    return quotes.get(id);
  }

  @Override
  public void save(Quote quote) {
    quotes.put(quote.getId(), quote);
  }
}
