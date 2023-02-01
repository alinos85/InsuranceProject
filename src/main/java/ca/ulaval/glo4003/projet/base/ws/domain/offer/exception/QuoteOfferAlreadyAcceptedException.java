package ca.ulaval.glo4003.projet.base.ws.domain.offer.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class QuoteOfferAlreadyAcceptedException extends ApiException {

  public final String ERROR = "Quote offer already accepted";

  public QuoteOfferAlreadyAcceptedException() {
    super("Can't accept a quote offer that has already been accepted");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
