package ca.ulaval.glo4003.projet.base.ws.domain.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidClaimStatusException extends ApiException {

  private String ERROR = "Invalid Spvq number";

  public InvalidClaimStatusException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}