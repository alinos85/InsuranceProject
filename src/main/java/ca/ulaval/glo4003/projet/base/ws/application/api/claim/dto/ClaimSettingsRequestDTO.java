package ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto;

import javax.validation.constraints.NotNull;

public class ClaimSettingsRequestDTO {

  @NotNull(message = "Le champ est requis")
  public double claimsAmountRatio;
}
