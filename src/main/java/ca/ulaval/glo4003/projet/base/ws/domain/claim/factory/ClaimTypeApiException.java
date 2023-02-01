package ca.ulaval.glo4003.projet.base.ws.domain.claim.factory;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimTypeApiException extends ApiException {

  private final String ERROR = "Invalid type";

  public ClaimTypeApiException(String type) {
    super(String.format("Claim type '%s' is invalid", type));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
