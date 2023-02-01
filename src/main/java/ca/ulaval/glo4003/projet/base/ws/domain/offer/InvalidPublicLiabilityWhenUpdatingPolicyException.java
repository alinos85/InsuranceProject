package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidPublicLiabilityWhenUpdatingPolicyException extends ApiException {

  private final String ERROR = "Can only increase public liability when updating policy";

  public InvalidPublicLiabilityWhenUpdatingPolicyException() {
    super("Can only increase public liability amount to 2000000 amount when updating policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
