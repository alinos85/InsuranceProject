package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class InvalidPublicLiabilityAmountException extends ApiException {

  private final String ERROR = "Invalid Public Liability Amount";

  public InvalidPublicLiabilityAmountException() {
    super("Public liability amount must be 1000000 or 2000000");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
