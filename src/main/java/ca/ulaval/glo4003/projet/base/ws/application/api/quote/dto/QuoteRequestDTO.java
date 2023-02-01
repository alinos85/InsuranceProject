package ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class QuoteRequestDTO {

  @NotNull
  public String effectiveDate;

  @Valid
  @NotNull(message = "L'objet est requis")
  public IdentityRequestDTO identity;

  @Valid
  @NotNull(message = "L'objet est requis")
  public AddressRequestDTO address;

  @Valid
  @NotNull(message = "L'objet est requis")
  public ApartementDetailsRequestDTO apartmentDetails;

  @Valid
  public List<AnimalRequestDTO> animals = new ArrayList<>();

  @NotNull(message = "Le champ est requis")
  public BigDecimal insuranceAmount;

  public BigDecimal publicLiabilityAmount;
}
