package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class PassEffectiveDateForInsurancePolicyException extends ApiException {

  public final String ERROR = "PASS_EFFECTIVE_DATE";

  public PassEffectiveDateForInsurancePolicyException() {
    super("Can't add an insurance policy if effective date is before today");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
