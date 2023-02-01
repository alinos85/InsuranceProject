package ca.ulaval.glo4003.projet.base.ws.domain.offer.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class NoChangeRequestedInUpdateException extends ApiException {

  public NoChangeRequestedInUpdateException() {
    super("Must include at least one change to update insurance policy");
  }

  @Override
  public String getError() {
    return "NO CHANGE REQUESTED";
  }
}
