package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class CantRenewInsurancePolicyDueToInactiveException extends ApiException {

  public final String ERROR = "CANT_RENEW_INSURANCE_NO_ACTIVE_INSURANCE";

  public CantRenewInsurancePolicyDueToInactiveException() {
    super("Can't renew insurance policy as you currently don't have any active insurance policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }

}
