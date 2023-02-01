package ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy;

import static java.time.temporal.ChronoUnit.DAYS;

import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;
import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;
import ca.ulaval.glo4003.projet.base.ws.domain.apartment.details.ApartmentDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.InvalidPublicLiabilityWhenUpdatingPolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.PublicLiability;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.exception.InvalidInsuranceAmountWhenUpdatingPolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.CantRenewInsurancePolicyDueToMore30DaysBeforeEndException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InsurancePolicy {

  private InsurancePolicyId id;
  private LocalDate effectiveDate;
  private LocalDate expirationDate;
  private Identity identity;
  private Address address;
  private ApartmentDetails apartmentDetails;
  private List<Animal> animals;
  private Money insuranceAmount;
  private PublicLiability publicLiability;
  private Money price;

  public InsurancePolicy(LocalDate effectiveDate, LocalDate expirationDate, Identity identity, Address address,
      ApartmentDetails apartmentDetails, List<Animal> animals, Money insuranceAmount,
      PublicLiability publicLiability, Money price) {
    this.id = new InsurancePolicyId();
    this.effectiveDate = effectiveDate;
    this.expirationDate = expirationDate;
    this.identity = identity;
    this.address = address;
    this.apartmentDetails = apartmentDetails;
    this.animals = animals;
    this.insuranceAmount = insuranceAmount;
    this.publicLiability = publicLiability;
    this.price = price;
  }

  public InsurancePolicyId getId() {
    return id;
  }

  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public Identity getIdentity() {
    return identity;
  }

  public Address getAddress() {
    return address;
  }

  public ApartmentDetails getApartmentDetails() {
    return apartmentDetails;
  }

  public List<Animal> getAnimals() {
    return animals;
  }

  public Money getInsuranceAmount() {
    return insuranceAmount;
  }

  public PublicLiability getPublicLiability() {
    return publicLiability;
  }

  public boolean isExpirationDateExceeded() {
    return !LocalDate.now().isBefore(this.getExpirationDate());
  }

  public boolean isEffectivePeriodOverlapping(InsurancePolicy insurancePolicy) {
    return this.getEffectiveDate().isBefore(insurancePolicy.getExpirationDate()) && insurancePolicy.getEffectiveDate().isBefore(this.getExpirationDate());
  }

  public Money getPrice() {
    return price;
  }

  public UpdateInsurancePolicyOffer generateUpdateInsurancePolicyOffer(OfferCalculator offerCalculator, Money newInsuranceAmount,
      BigDecimal newPublicLiability) {

    if (newInsuranceAmount != null && !this.insuranceAmount.isSmaller(newInsuranceAmount)) {
      throw new InvalidInsuranceAmountWhenUpdatingPolicyException();
    }

    if (newPublicLiability != null && newPublicLiability.intValue() != 2000000) {
      throw new InvalidPublicLiabilityWhenUpdatingPolicyException();
    }

    return offerCalculator.calculatedUpdateInsurancePolicy(this, newInsuranceAmount, newPublicLiability);
  }

  public RenewInsurancePolicyOffer generateRenewInsurancePolicyOffer(OfferCalculator offerCalculator, Money newInsuranceAmount) {
    if (DAYS.between(LocalDate.now(), this.expirationDate) > 30) {
      throw new CantRenewInsurancePolicyDueToMore30DaysBeforeEndException();
    }

    return offerCalculator.calculatedRenewalInsurancePolicy(this, newInsuranceAmount);
  }
}
