package ca.ulaval.glo4003.projet.base.ws.domain.offer.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class OfferAlreadyAcceptedException extends ApiException {

  public final String ERROR = "Offer already accepted";

  public OfferAlreadyAcceptedException() {
    super("Can't accept an offer that has already been accepted");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
