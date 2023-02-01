package ca.ulaval.glo4003.projet.base.ws.domain.student.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class NoActiveInsurancePolicyException extends ApiException {

  public final String ERROR = "NO_ACTIVE_INSURANCE_POLICY";

  public NoActiveInsurancePolicyException() {
    super(String.format("An active insurance policy is necessary to make a claim"));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}