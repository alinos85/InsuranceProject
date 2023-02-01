package ca.ulaval.glo4003.projet.base.ws.domain.authentification;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class TokenInvalidException extends ApiException {

  public final String ERROR = "TOKEN_INVALID";

  public TokenInvalidException() {
    super("The auth token is invalid.");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}