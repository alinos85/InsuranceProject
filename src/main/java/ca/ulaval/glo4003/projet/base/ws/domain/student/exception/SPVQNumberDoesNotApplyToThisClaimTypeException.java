package ca.ulaval.glo4003.projet.base.ws.domain.student.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class SPVQNumberDoesNotApplyToThisClaimTypeException extends ApiException {

  private String ERROR = "Spvq number does not apply to this claim type";

  public SPVQNumberDoesNotApplyToThisClaimTypeException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}