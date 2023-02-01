package ca.ulaval.glo4003.projet.base.ws.service.claim;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.claim.assembler.ClaimAssembler;

public class GetClaimStatus {

  private ClaimAssembler claimAssembler;
  private UserRepository userRepository;

  public GetClaimStatus(ClaimAssembler claimAssembler, UserRepository userRepository) {
    this.claimAssembler = claimAssembler;
    this.userRepository = userRepository;
  }

  public String getClaimStatus(String userEmail, String id) {
    Student student = userRepository.findStudentByEmail(userEmail);

    ClaimId claimId = new ClaimId(id);
    Claim claim = student.getClaim(claimId);

    return claim.getStatus();
  }
}
