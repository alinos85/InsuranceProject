package ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto;

import javax.validation.constraints.NotNull;

public class SPVQNumberRequestDTO {

  @NotNull(message = "Le champ est requis")
  public String spvqNumber;
}
