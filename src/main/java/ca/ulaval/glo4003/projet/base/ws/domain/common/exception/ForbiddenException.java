package ca.ulaval.glo4003.projet.base.ws.domain.common.exception;

public abstract class ForbiddenException extends ApiException {

  public ForbiddenException(String message) {
    super(message);
  }
}
