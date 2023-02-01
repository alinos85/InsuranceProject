package ca.ulaval.glo4003.projet.base.ws.domain.common.email;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class EmailInvalidException extends ApiException {

  public final String ERROR = "EMAIL_NOT_FOUND";

  public EmailInvalidException(String email) {
    super(String.format("The email '%s' doesn't match any user", email));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}