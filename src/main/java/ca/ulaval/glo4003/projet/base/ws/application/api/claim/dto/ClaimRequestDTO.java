package ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class ClaimRequestDTO {

  @Valid
  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public List<ClaimValueDetailsRequestDTO> claimValuesDetailsDTO;

  @NotNull(message = "Le champ est requis")
  @NotEmpty(message = "Le champ ne doit pas être vide")
  public String claimType;
}
