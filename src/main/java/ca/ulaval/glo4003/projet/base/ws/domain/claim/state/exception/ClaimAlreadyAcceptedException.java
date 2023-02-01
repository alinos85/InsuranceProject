package ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimAlreadyAcceptedException extends ApiException {

  private String ERROR = "Claim already accepted";

  public ClaimAlreadyAcceptedException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }

}
