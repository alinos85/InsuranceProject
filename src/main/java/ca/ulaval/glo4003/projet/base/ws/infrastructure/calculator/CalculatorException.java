package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.ApiException;

public class CalculatorException extends ApiException {

  public CalculatorException() {
    super("Calculated error", "Error Server", 500);
  }

}
