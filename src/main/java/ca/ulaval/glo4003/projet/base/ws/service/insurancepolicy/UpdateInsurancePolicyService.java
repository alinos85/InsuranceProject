package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.NoChangeRequestedInUpdateException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import java.math.BigDecimal;

public class UpdateInsurancePolicyService {

  private UserRepository userRepository;
  private OfferCalculator calculator;
  private OfferAssembler offerAssembler;

  public UpdateInsurancePolicyService(UserRepository userRepository, OfferCalculator calculator, OfferAssembler offerAssembler) {
    this.userRepository = userRepository;
    this.calculator = calculator;
    this.offerAssembler = offerAssembler;
  }

  public OfferResponseDTO updateInsurancePolicyRequest(String userEmail, BigDecimal newInsuranceAmount, BigDecimal newPublicLiabilityAmount) {

    if (newInsuranceAmount == null && newPublicLiabilityAmount == null) {
      throw new NoChangeRequestedInUpdateException();
    }

    Student student = userRepository.findStudentByEmail(userEmail);

    UpdateInsurancePolicyOffer updateInsurancePolicyOffer = student.updateInsurancePolicy(
        calculator,
        newInsuranceAmount != null ? new Money(newInsuranceAmount) : null,
        newPublicLiabilityAmount
    );

    userRepository.save(student);

    return offerAssembler.create(updateInsurancePolicyOffer);
  }
}
