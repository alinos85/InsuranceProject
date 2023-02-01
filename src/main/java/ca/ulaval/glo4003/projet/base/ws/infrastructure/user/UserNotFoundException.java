package ca.ulaval.glo4003.projet.base.ws.infrastructure.user;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {

  public final String ERROR = "USER_NOT_FOUND";

  public UserNotFoundException() {
    super("The user was not found");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
