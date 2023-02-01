package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class InvalidInsuranceAmountWhenUpdatingPolicyException extends ApiException {

  private final String ERROR = "Can only increase insurance amount when updating policy";

  public InvalidInsuranceAmountWhenUpdatingPolicyException() {
    super("Can only increase insurance amount when updating policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
