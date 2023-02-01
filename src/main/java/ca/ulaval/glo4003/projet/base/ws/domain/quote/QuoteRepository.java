package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import java.util.List;

public interface QuoteRepository {

  List<Quote> findAll();

  Quote findById(String id);

  void save(Quote quote);
}
