package ca.ulaval.glo4003.projet.base.ws.domain.common;

public abstract class ApiException extends RuntimeException {

  public ApiException(String message) {
    super(message);
  }

  public abstract String getError();
}
