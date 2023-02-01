package ca.ulaval.glo4003.projet.base.ws.domain.offer.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ResourceNotFoundException;

public class OfferNotFoundExeption extends ResourceNotFoundException {

  private final String ERROR = "Offer not found";

  public OfferNotFoundExeption(String offerId) {
    super(String.format("The offer with id '%s' was not found", offerId));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
