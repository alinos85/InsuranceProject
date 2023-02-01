package ca.ulaval.glo4003.projet.base.ws.domain.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ResourceNotFoundException;

public class ClaimNotFoundExeption extends ResourceNotFoundException {

  private final String ERROR = "Claim not found";

  public ClaimNotFoundExeption(String claimId) {
    super(String.format("The claim with id '%s' doesn't exist", claimId));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
