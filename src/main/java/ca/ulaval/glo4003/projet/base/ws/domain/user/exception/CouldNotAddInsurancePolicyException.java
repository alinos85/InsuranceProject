package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class CouldNotAddInsurancePolicyException extends ApiException {

  public final String ERROR = "COULD_NOT_ADD_INSURANCE";

  public CouldNotAddInsurancePolicyException() {
    super("Could not add insurance policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
