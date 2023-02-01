package ca.ulaval.glo4003.projet.base.ws.domain.quote.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class QuoteImpossibleToGenerateException extends ApiException {

  private final String ERROR = "QUOTE_IMPOSSIBLE_TO_GENERATE";

  public QuoteImpossibleToGenerateException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
