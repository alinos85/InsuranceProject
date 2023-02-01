package ca.ulaval.glo4003.projet.base.ws.infrastructure.user;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ForbiddenException;

public class UserTypeIsNotAuthorized extends ForbiddenException {

  public final String ERROR = "USER_TYPE_UNAUTHORIZED";

  public UserTypeIsNotAuthorized(String authorizedUserType) {
    super(String.format("User need to be %s to do this an action", authorizedUserType));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
