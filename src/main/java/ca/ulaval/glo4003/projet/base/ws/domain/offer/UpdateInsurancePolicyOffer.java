package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferAlreadyAcceptedException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferExpiredException;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.PublicLiability;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateInsurancePolicyOffer implements Offer {

  private OfferId offerId;
  private Money value;
  private boolean accepted;
  private InsurancePolicy oldInsurancePolicy;
  private Money newInsuranceAmount;
  private BigDecimal newPublicLiabilityAmount;

  public UpdateInsurancePolicyOffer(InsurancePolicy insurancePolicy, Money value, Money newInsuranceAmount, BigDecimal newPublicLiabilityAmount) {
    this.newInsuranceAmount = newInsuranceAmount;
    this.newPublicLiabilityAmount = newPublicLiabilityAmount;
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
      throw new OfferExpiredException("Offer is expired because the insurance policy to update isn't active anymore");
    }
    InsurancePolicy insurancePolicy = this.generateInsurancePolicy();
    student.updateInsurancePolicy(insurancePolicy);
    accepted = true;
    return insurancePolicy;
  }

  private InsurancePolicy generateInsurancePolicy() {
    return new InsurancePolicy(
        LocalDate.now(),
        this.oldInsurancePolicy.getExpirationDate(),
        this.oldInsurancePolicy.getIdentity(),
        this.oldInsurancePolicy.getAddress(),
        this.oldInsurancePolicy.getApartmentDetails(),
        this.oldInsurancePolicy.getAnimals(),
        this.newInsuranceAmount != null ? this.newInsuranceAmount : this.oldInsurancePolicy.getInsuranceAmount(),
        this.newPublicLiabilityAmount != null ? new PublicLiability(this.newPublicLiabilityAmount) : this.oldInsurancePolicy.getPublicLiability(),
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
