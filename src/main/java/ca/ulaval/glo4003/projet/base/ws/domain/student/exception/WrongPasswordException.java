package ca.ulaval.glo4003.projet.base.ws.domain.student.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class WrongPasswordException extends ApiException {

  public final String ERROR = "WRONG_PASSWORD";

  public WrongPasswordException() {
    super("The password is wrong.");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
