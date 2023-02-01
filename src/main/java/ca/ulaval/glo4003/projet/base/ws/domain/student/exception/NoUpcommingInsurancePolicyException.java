package ca.ulaval.glo4003.projet.base.ws.domain.student.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class NoUpcommingInsurancePolicyException extends ApiException {

  public final String ERROR = "NO_UPCOMING_INSURANCE_POLICY";

  public NoUpcommingInsurancePolicyException() {
    super("You have no upcoming insurance policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
