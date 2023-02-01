package ca.ulaval.glo4003.projet.base.ws.domain.common.email;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class EmailFormatInvalidException extends ApiException {

  public final String ERROR = "EMAIL_FORMAT_INVALID";

  public EmailFormatInvalidException(String email) {
    super(String.format("The email '%s' format is invalis", email));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}