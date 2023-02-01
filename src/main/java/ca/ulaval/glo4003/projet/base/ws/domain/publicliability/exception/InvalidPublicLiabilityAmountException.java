package ca.ulaval.glo4003.projet.base.ws.domain.publicliability.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidPublicLiabilityAmountException extends ApiException {

  private final String ERROR = "Invalid public liability amount";

  public InvalidPublicLiabilityAmountException() {
    super("Invalid public liability amount");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
