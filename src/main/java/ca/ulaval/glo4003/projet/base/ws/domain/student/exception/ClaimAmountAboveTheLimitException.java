package ca.ulaval.glo4003.projet.base.ws.domain.student.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimAmountAboveTheLimitException extends ApiException {

  public final String ERROR = "Claim amount above the amount limit ";

  public ClaimAmountAboveTheLimitException(String message) {
    super(String.format(message));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
