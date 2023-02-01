package ca.ulaval.glo4003.projet.base.ws.domain.quote.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidInitialPublicLiabilityException extends ApiException {

  private final String ERROR = "Invalid initial Public Liability Amount";

  public InvalidInitialPublicLiabilityException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
