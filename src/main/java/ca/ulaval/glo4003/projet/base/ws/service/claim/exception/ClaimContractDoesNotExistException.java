package ca.ulaval.glo4003.projet.base.ws.service.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimContractDoesNotExistException extends ApiException {

  public final String ERROR = "Claim contract doesn't exist";

  public ClaimContractDoesNotExistException(String contractId) {
    super(String.format("The id '%s' doesn't match to any contract", contractId));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
