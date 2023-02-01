package ca.ulaval.glo4003.projet.base.ws.domain.claim.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class ClaimAmountRatioInvalid extends ApiException {

  public final String ERROR = "CLAIM_AMOUNT_RATIO_INVALID";

  public ClaimAmountRatioInvalid(String message) {
    super(message);
  }

  @Override
  public String getError() {
    return ERROR;
  }
}