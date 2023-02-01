package ca.ulaval.glo4003.projet.base.ws.domain.publicliability.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidUpdatingPublicLiabilityAmountException extends ApiException {

  private final String ERROR = "You can not update your public liability amount";

  public InvalidUpdatingPublicLiabilityAmountException() {
    super("You can not update your public liability amount");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
