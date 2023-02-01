package ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

public class RenewInsurancePolicyRequestDTO {

  @NotNull(message = "Le champ est requis")
  public BigDecimal insuranceAmount;
}
