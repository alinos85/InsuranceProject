package ca.ulaval.glo4003.projet.base.ws.service.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimDoesNotExistException extends ApiException {

  private String ERROR = "Claim does not exist";

  public ClaimDoesNotExistException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
