package ca.ulaval.glo4003.projet.base.ws.domain.user.exception;

import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class EffectivePeriodForUpcomingInsurancePolicyOverlapCurrentEffectiveDateException extends ApiException {

  public final String ERROR = "OVERLAP_EFFECTICVE_DATE_ACTIVE_INSURANCE";

  public EffectivePeriodForUpcomingInsurancePolicyOverlapCurrentEffectiveDateException() {
    super("Can't add upcoming insurance policy as effective period is overlapping active insurance policy effective period");
  }

  @Override
  public String getError() {
    return ERROR;
  }
}
