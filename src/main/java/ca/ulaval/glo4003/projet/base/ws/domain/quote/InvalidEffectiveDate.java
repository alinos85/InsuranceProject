package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.common.ApiException;

public class InvalidEffectiveDate extends ApiException {

  private String ERROR = "Effective date is invalid";

  public InvalidEffectiveDate(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
