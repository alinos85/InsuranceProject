package ca.ulaval.glo4003.projet.base.ws.service.user;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ResourceConflitException;

public class UserAlreadyExistException extends ResourceConflitException {

  private String ERROR = "This email address is already used";

  public UserAlreadyExistException(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}