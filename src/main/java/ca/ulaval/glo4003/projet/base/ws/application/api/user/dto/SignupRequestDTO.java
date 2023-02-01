package ca.ulaval.glo4003.projet.base.ws.application.api.user.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SignupRequestDTO {

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  @Email(message = "Le courriel est invalide")
  public String email;

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String password;
}
