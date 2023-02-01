package ca.ulaval.glo4003.projet.base.ws.domain.quote.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidEffectiveDateException extends ApiException {

  private String ERROR = "Effective date is invalid";

  public InvalidEffectiveDateException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
