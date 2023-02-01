package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class CantUpdateInsurancePolicyDueToInactiveException extends ApiException {

  public final String ERROR = "CANT_UPDATE_WITHOUT_ACTIVE_INSURANCE";

  public CantUpdateInsurancePolicyDueToInactiveException() {
    super("Can't update insurance policy as you currently don't have any active insurance policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }

}
