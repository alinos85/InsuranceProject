package ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class ClaimValueDetailsRequestDTO {

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String name;

  @NotNull(message = "Le champ est requis")
  public BigDecimal amount;
}
