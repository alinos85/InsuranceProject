package ca.ulaval.glo4003.projet.base.ws.domain.offer.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class OfferExpiredException extends ApiException {

  public final String ERROR = "Offer already accepted";

  public OfferExpiredException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
