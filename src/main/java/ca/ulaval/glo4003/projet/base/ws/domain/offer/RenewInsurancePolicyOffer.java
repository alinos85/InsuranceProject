package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferAlreadyAcceptedException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferExpiredException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;

public class RenewInsurancePolicyOffer implements Offer {

  OfferId offerId;
  Money value;
  boolean accepted;
  InsurancePolicy oldInsurancePolicy;

  public RenewInsurancePolicyOffer(Money value, InsurancePolicy insurancePolicy) {
    this.offerId = new OfferId();
    this.value = value;
    this.accepted = false;
    oldInsurancePolicy = insurancePolicy;
  }

  @Override
  public OfferId getOfferId() {
    return offerId;
  }

  @Override
  public InsurancePolicy accept(Student student) {
    if (this.isOfferAccepted()) {
      throw new OfferAlreadyAcceptedException();
    }
    if (!student.isInsurancePolicyActive(this.oldInsurancePolicy.getId())) {
      throw new OfferExpiredException("Offer is expired because the insurance policy to renew isn't active anymore");
    }

    InsurancePolicy insurancePolicy = this.generateInsurancePolicy();

    student.addInsurancePolicy(insurancePolicy);
    accepted = true;

    return insurancePolicy;
  }

  private InsurancePolicy generateInsurancePolicy() {
    return new InsurancePolicy(
        this.oldInsurancePolicy.getExpirationDate(),
        this.oldInsurancePolicy.getExpirationDate().plusYears(1),
        this.oldInsurancePolicy.getIdentity(),
        this.oldInsurancePolicy.getAddress(),
        this.oldInsurancePolicy.getApartmentDetails(),
        this.oldInsurancePolicy.getAnimals(),
        this.oldInsurancePolicy.getInsuranceAmount(),
        this.oldInsurancePolicy.getPublicLiability(),
        this.getValue());
  }

  @Override
  public Money getValue() {
    return value;
  }

  @Override
  public boolean isOfferAccepted() {
    return accepted;
  }
}
