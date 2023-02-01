package ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class AnimalRequestDTO {

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String animal;
}
