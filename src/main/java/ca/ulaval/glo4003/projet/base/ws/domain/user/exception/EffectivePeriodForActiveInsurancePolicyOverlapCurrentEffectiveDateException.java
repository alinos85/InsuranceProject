package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class EffectivePeriodForActiveInsurancePolicyOverlapCurrentEffectiveDateException extends ApiException {

  public final String ERROR = "OVERLAP_EFFECTICVE_DATE_UPCOMING_INSURANCE";

  public EffectivePeriodForActiveInsurancePolicyOverlapCurrentEffectiveDateException() {
    super("Can't add active insurance policy as effective period is overlapping upcoming insurance policy effective period");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
