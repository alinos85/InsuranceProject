package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;

public class AcceptInsurancePolicy {

  private UserRepository userRepository;
  private QuoteOfferRepository quoteOfferRepository;
  private InsurancePolicyAssembler insurancePolicyAssembler;

  public AcceptInsurancePolicy(UserRepository userRepository, QuoteOfferRepository quoteOfferRepository, InsurancePolicyAssembler insurancePolicyAssembler) {
    this.userRepository = userRepository;
    this.quoteOfferRepository = quoteOfferRepository;
    this.insurancePolicyAssembler = insurancePolicyAssembler;
  }

  public InsurancePolicyResponseDTO acceptOffer(String userEmail, String quoteOfferId) {
    Student student = userRepository.findStudentByEmail(userEmail);

    QuoteOffer quoteOffer = this.quoteOfferRepository.findById(
        new OfferId(quoteOfferId)
    );

    InsurancePolicy insurancePolicy = student.acceptQuoteOffer(quoteOffer);

    userRepository.save(student);
    this.quoteOfferRepository.delete(quoteOffer.getOfferId());

    return insurancePolicyAssembler.create(insurancePolicy);
  }
}
