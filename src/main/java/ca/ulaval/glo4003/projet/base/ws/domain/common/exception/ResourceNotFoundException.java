package ca.ulaval.glo4003.projet.base.ws.domain.common.exception;

public abstract class ResourceNotFoundException extends ApiException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
