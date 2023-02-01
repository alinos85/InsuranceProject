package ca.ulaval.glo4003.projet.base.ws.service.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimContractNotAcceptedException extends ApiException {

  public final String ERROR = "Contract is not accepted";

  public ClaimContractNotAcceptedException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
