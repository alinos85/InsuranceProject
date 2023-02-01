package ca.ulaval.glo4003.projet.base.ws.domain.apartment.details;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class InvalidNumberOfHousingException extends ApiException {

  private final String ERROR = "Invalid number of housing";

  public InvalidNumberOfHousingException() {
    super("Invalid number of housing");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
