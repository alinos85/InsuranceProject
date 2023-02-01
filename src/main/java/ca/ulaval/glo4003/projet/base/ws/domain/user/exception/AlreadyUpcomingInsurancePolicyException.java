package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class AlreadyUpcomingInsurancePolicyException extends ApiException {

  public final String ERROR = "ALREADY_UPCOMING_INSURANCE";

  public AlreadyUpcomingInsurancePolicyException() {
    super("There is already an upcoming insurance policy");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
