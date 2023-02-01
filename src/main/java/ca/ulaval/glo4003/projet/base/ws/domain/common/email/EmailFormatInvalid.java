package ca.ulaval.glo4003.projet.base.ws.domain.common.email;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class EmailFormatInvalid extends ApiException {

  public final String ERROR = "EMAIL_FORMAT_INVALID";

  public EmailFormatInvalid(String email) {
    super(String.format("The email '%s' format is invalis", email));
  }

  @Override
  public String getError() {
    return ERROR;
  }
}