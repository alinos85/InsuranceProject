package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class CantRenewInsurancePolicyDueToMore30DaysBeforeEndException extends ApiException {

  public final String ERROR = "CANT_RENEW_INSURANCE_30_BEFORE_END_ACTIVE_INSURANCE";

  public CantRenewInsurancePolicyDueToMore30DaysBeforeEndException() {
    super("The renewal process can be made 30 days before expiration date");
  }

  @Override
  public String getError() {
    return ERROR;
  }

}
