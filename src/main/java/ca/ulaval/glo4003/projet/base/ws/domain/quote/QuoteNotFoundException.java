package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class QuoteNotFoundException extends ApiException {

  private final String ERROR = "Quote not found";

  public QuoteNotFoundException(String quoteId) {
    super(String.format("The quote with id '%s' doesn't exist", quoteId));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
