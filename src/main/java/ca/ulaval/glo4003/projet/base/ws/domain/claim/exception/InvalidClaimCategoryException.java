package ca.ulaval.glo4003.projet.base.ws.domain.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidClaimCategoryException extends ApiException {

  private final String ERROR = "invalid category";

  public InvalidClaimCategoryException(String name) {
    super(String.format("The claim category %s doesn't exist", name));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
