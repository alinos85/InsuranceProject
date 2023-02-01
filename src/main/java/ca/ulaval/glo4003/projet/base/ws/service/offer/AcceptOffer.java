package ca.ulaval.glo4003.projet.base.ws.service.offer;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.InsurancePolicyAssembler;

public class AcceptOffer {

  private UserRepository userRepository;
  private InsurancePolicyAssembler insurancePolicyAssembler;

  public AcceptOffer(UserRepository userRepository, InsurancePolicyAssembler insurancePolicyAssembler) {

    this.userRepository = userRepository;
    this.insurancePolicyAssembler = insurancePolicyAssembler;
  }

  public InsurancePolicyResponseDTO acceptOffer(String email, String offerId) {

    Student student = userRepository.findStudentByEmail(email);

    InsurancePolicy insurancePolicy = student.acceptOffer(
        new OfferId(offerId)
    );

    userRepository.save(student);

    return insurancePolicyAssembler.create(insurancePolicy);
  }

}
