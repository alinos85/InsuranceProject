package ca.ulaval.glo4003.projet.base.ws.service.claim;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimSettings;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.claim.assembler.ClaimAssembler;

public class GenerateClaim {

  private ClaimAssembler claimAssembler;
  private UserRepository userRepository;
  private ClaimSettings claimSettings;

  public GenerateClaim(ClaimAssembler claimAssembler, UserRepository userRepository, ClaimSettings claimSettings) {
    this.claimAssembler = claimAssembler;
    this.userRepository = userRepository;
    this.claimSettings = claimSettings;
  }

  public String addClaim(String userEmail, ClaimRequestDTO claimRequestDTO) {
    Student student = userRepository.findStudentByEmail(userEmail);
    Claim claim = this.claimAssembler.fromDTO(claimRequestDTO);

    student.addClaim(claim, claimSettings.getClaimAmountRatio());

    return claim.getId().value;
  }
}
