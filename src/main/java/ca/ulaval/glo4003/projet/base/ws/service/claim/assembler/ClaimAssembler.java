package ca.ulaval.glo4003.projet.base.ws.service.claim.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimIdResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimsResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.factory.ClaimFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimType;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.MoneyAssembler;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClaimAssembler {

  private ClaimValueDetailsAssembler claimValueDetailsAssembler;
  private MoneyAssembler moneyAssembler;

  public ClaimAssembler() {
    this.moneyAssembler = new MoneyAssembler();
    claimValueDetailsAssembler = new ClaimValueDetailsAssembler(moneyAssembler);
  }

  public ClaimRequestDTO toDTO(Claim claim) {
    ClaimRequestDTO claimRequestDTO = new ClaimRequestDTO();
    claimRequestDTO.claimType = claim.getClaimType().toString();
    claimRequestDTO.claimValuesDetailsDTO = claim.getClaimValueDetails().stream()
        .map(this.claimValueDetailsAssembler::toDTO)
        .collect(Collectors.toList());
    return claimRequestDTO;
  }

  public ClaimsResponseDTO toDTO(List<Claim> claims) {
    ClaimsResponseDTO claimsResponseDTO = new ClaimsResponseDTO();
    List<ClaimIdResponseDTO> claimsResponse = new ArrayList<>();
    for (Claim claim : claims) {
      ClaimIdResponseDTO claimIdResponseDTO = new ClaimIdResponseDTO();
      claimIdResponseDTO.idClaim = claim.getId().value;
      claimsResponse.add(claimIdResponseDTO);
    }
    claimsResponseDTO.claimsInAnalysis = claimsResponse;
    return claimsResponseDTO;
  }

  public Claim fromDTO(ClaimRequestDTO claimRequestDTO) {
    Claim claim = ClaimFactory.buildClaim(claimRequestDTO.claimType);
    claim.setClaimType(Enum.valueOf(ClaimType.class, claimRequestDTO.claimType));
    claim.setClaimValueDetails(claimRequestDTO.claimValuesDetailsDTO.stream()
        .map(this.claimValueDetailsAssembler::fromDTO)
        .collect(Collectors.toList()));
    claim.setClaimStatus();
    claim.defineClaimTotalAmount();
    return claim;
  }
}
