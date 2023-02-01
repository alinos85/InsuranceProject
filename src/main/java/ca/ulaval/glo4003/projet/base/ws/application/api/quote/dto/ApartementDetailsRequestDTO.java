package ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ApartementDetailsRequestDTO {

  @NotNull(message = "Le champ est requis")
  @Min(value = 1, message = "Minimum de 1")
  public Integer nbOfHousing;

  @NotNull(message = "Le champ est requis")
  public Boolean hasCommerce;

  @NotNull(message = "Le champ est requis")
  public Boolean hasJet;

  @NotNull(message = "Le champ est requis")
  public Boolean hasAlarm;
}
