package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class AlreadyActiveInsurancePolicyException extends ApiException {

  public final String ERROR = "ALREADY_ACTIVE_INSURANCE";

  public AlreadyActiveInsurancePolicyException() {
    super("There is already an active insurance policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }

}
