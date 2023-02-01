package ca.ulaval.glo4003.projet.base.ws.service.actuary;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimsResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimSettings;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.claim.assembler.ClaimAssembler;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UpdateClaimAmountRatio {

  private UserRepository userRepository;
  private ClaimSettings claimSettings;
  private ClaimAssembler claimAssembler;

  public UpdateClaimAmountRatio(UserRepository userRepository, ClaimSettings claimSettings,
      ClaimAssembler claimAssembler) {
    this.userRepository = userRepository;
    this.claimSettings = claimSettings;
    this.claimAssembler = claimAssembler;
  }

  public ClaimsResponseDTO update(String userEmail, double claimAmountRatio) {
    userRepository.findActuaryByEmail(userEmail);
    claimSettings.setClaimAmountRatio(claimAmountRatio);
    List<Claim> claimsInAnalysisWithHigherAmountThanRatio = getClaimsInAnalysisWithHigherAmountThanRatio(claimAmountRatio);
    return claimAssembler.toDTO(claimsInAnalysisWithHigherAmountThanRatio);
  }

  private List<Claim> getClaimsInAnalysisWithHigherAmountThanRatio(double claimAmountRatio) {
    List<Student> students = userRepository.findAllStudents();
    List<Claim> claimsInAnalysisWithHigherAmountThanRatio = new ArrayList<>();
    for (Student student : students) {
      Stream.of(claimsInAnalysisWithHigherAmountThanRatio, student.getClaimsInAnalysisWithHigherAmountThanRatio(claimAmountRatio))
          .forEach(claimsInAnalysisWithHigherAmountThanRatio::addAll);
    }
    return claimsInAnalysisWithHigherAmountThanRatio;
  }
}
