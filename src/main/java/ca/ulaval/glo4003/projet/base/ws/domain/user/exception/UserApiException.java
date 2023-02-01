package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class UserApiException extends ApiException {

  public final String ERROR = "USER_DOES_NOT_EXIST";

  public UserApiException(String email) {
    super(String.format("The email '%s' doesn't match any user", email));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
