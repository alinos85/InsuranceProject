package ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class IdentityRequestDTO {

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String name;

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String birthday;

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String gender;

  @Email(message = "Le courriel est invalide")
  public String email;
}
