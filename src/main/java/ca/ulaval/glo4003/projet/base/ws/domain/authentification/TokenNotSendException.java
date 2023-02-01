package ca.ulaval.glo4003.projet.base.ws.domain.authentification;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class TokenNotSendException extends ApiException {

  public final String ERROR = "TOKEN_NOT_SENT";

  public TokenNotSendException() {
    super("The auth token is not sent.");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}