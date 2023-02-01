package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import java.math.BigDecimal;

public class RenewInsurancePolicyService {

  private UserRepository userRepository;
  private OfferCalculator calculator;
  private OfferAssembler offerAssembler;

  public RenewInsurancePolicyService(UserRepository userRepository, OfferCalculator calculator, OfferAssembler offerAssembler) {
    this.userRepository = userRepository;
    this.calculator = calculator;
    this.offerAssembler = offerAssembler;
  }

  public OfferResponseDTO renewInsurancePolicyRequest(String userEmail, BigDecimal newInsuranceAmount) {
    Student student = userRepository.findStudentByEmail(userEmail);

    RenewInsurancePolicyOffer renewInsurancePolicyOffer = student.requestRenewInsurancePolicy(
        calculator,
        new Money(newInsuranceAmount)
    );

    userRepository.save(student);

    return offerAssembler.create(renewInsurancePolicyOffer);
  }
}
