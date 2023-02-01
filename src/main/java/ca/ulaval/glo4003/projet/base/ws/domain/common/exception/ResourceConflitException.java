package ca.ulaval.glo4003.projet.base.ws.domain.common.exception;

public abstract class ResourceConflitException extends ApiException {

  public ResourceConflitException(String message) {
    super(message);
  }
}
