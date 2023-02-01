package ca.ulaval.glo4003.projet.base.ws.service.claim.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimValueDetailsRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetailsFactory;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.MoneyAssembler;

public class ClaimValueDetailsAssembler {

  private MoneyAssembler moneyAssembler;

  public ClaimValueDetailsAssembler(MoneyAssembler moneyAssembler) {
    this.moneyAssembler = moneyAssembler;
  }

  public ClaimValueDetailsRequestDTO toDTO(ClaimValueDetails claimValueDetails) {
    ClaimValueDetailsRequestDTO claimValueDetailsDTO = new ClaimValueDetailsRequestDTO();
    claimValueDetailsDTO.name = claimValueDetails.getName();
    claimValueDetailsDTO.amount = moneyAssembler.create(claimValueDetails.getAmount());
    return claimValueDetailsDTO;
  }

  public ClaimValueDetails fromDTO(ClaimValueDetailsRequestDTO claimValueDetailsDTO) {
    ClaimValueDetails claimValueDetails = ClaimValueDetailsFactory.buildClaimValueDetails(claimValueDetailsDTO.name);
    claimValueDetails.setAmount(moneyAssembler.create(claimValueDetailsDTO.amount));
    return claimValueDetails;
  }
}
