package ca.ulaval.glo4003.projet.base.ws.domain.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimContractApiException extends ApiException {

  public final String ERROR = "Claim contract doesn't exist";

  public ClaimContractApiException(String contractId) {
    super(String.format("The id '%s' doesn't match to any contract", contractId));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
