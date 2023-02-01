package ca.ulaval.glo4003.projet.base.ws.domain.spvq;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidSPVQNumberException extends ApiException {

  private String ERROR = "Invalid Spvq number";

  public InvalidSPVQNumberException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}