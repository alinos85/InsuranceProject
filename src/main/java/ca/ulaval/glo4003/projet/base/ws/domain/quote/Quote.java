package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;
import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;
import ca.ulaval.glo4003.projet.base.ws.domain.apartment.details.ApartmentDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.PublicLiability;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.exception.InvalidUpdatingPublicLiabilityAmountException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.exception.InvalidEffectiveDateException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.exception.InvalidInitialPublicLiabilityException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Quote implements Cloneable {

  private final String INVALID_EFFECTIVE_DATE = "Effective date cannot be before today";

  private String id;
  private LocalDate effectiveDate;
  private Identity identity;
  private Address address;
  private ApartmentDetails apartmentDetails;
  private List<Animal> animals;
  private Money insuranceAmount;
  private PublicLiability publicLiability;

  public Quote(LocalDate effectiveDate, Identity identity, Address address, ApartmentDetails apartmentDetails, List<Animal> animals, Money insuranceAmount,
      Money publicLiabilityAmount) {

    this(effectiveDate, identity, address, apartmentDetails, animals, insuranceAmount);
    this.changeInitialPublicLiability(publicLiabilityAmount);
  }

  public Quote(LocalDate effectiveDate, Identity identity, Address address, ApartmentDetails apartmentDetails, List<Animal> animals, Money insuranceAmount) {

    if (effectiveDate.isBefore(LocalDate.now())) {
      throw new InvalidEffectiveDateException(INVALID_EFFECTIVE_DATE);
    }

    this.id = UUID.randomUUID().toString();
    this.effectiveDate = effectiveDate;
    this.identity = identity;
    this.address = address;
    this.apartmentDetails = apartmentDetails;
    this.animals = animals;
    this.insuranceAmount = insuranceAmount;
    setDefaultPublicLiability();
  }

  // for clone Only
  public Quote(LocalDate effectiveDate, Identity identity, Address address, ApartmentDetails apartmentDetails, List<Animal> animals, Money insuranceAmount,
      PublicLiability publicLiability) {
    this.id = UUID.randomUUID().toString();
    this.effectiveDate = effectiveDate;
    this.identity = identity;
    this.address = address;
    this.apartmentDetails = apartmentDetails;
    this.animals = animals;
    this.insuranceAmount = insuranceAmount;
    this.publicLiability = publicLiability;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(LocalDate effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Identity identity) {
    this.identity = identity;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
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

  private void setDefaultPublicLiability() {
    if (this.getApartmentDetails().getNbOfHousing() < 25) {
      this.publicLiability = new PublicLiability(1000000);
    } else {
      this.publicLiability = new PublicLiability(2000000);
    }
  }

  private void changeInitialPublicLiability(Money newAmount) {

    if (isVerySmallHousing()) {
      throw new InvalidUpdatingPublicLiabilityAmountException();
    } else if (isSmallHousing()) {
      if (newAmount.isEqual(new Money(2000000))) {
        this.publicLiability = new PublicLiability(2000000);
      } else {
        throw new InvalidInitialPublicLiabilityException(
            "You can only change your initial Public Liability from 1000000 to 2000000 with apartment between 3 and 25 units");
      }
    } else if (isMediumHousing()) {
      if (newAmount.isEqual(new Money(1000000)) && !this.getPublicLiability().isEqual(new BigDecimal(1000000))) {
        this.publicLiability = new PublicLiability(1000000);
      } else {
        throw new InvalidInitialPublicLiabilityException(
            "You can only change your initial Public Liability from 2000000 to 1000000 with apartment between 25 and 50 units");
      }
    } else if (isLargeHousing()) {
      throw new InvalidUpdatingPublicLiabilityAmountException();
    }
  }

  public Quote clone() {
    return new Quote(this.effectiveDate, this.identity, this.address, this.apartmentDetails, this.animals, this.insuranceAmount, this.publicLiability);
  }

  private boolean isLargeHousing() {
    return this.apartmentDetails.getNbOfHousing() >= 50;
  }

  private boolean isMediumHousing() {
    return this.apartmentDetails.getNbOfHousing() >= 25 && this.apartmentDetails.getNbOfHousing() < 50;
  }

  private boolean isSmallHousing() {
    return this.apartmentDetails.getNbOfHousing() > 3 && this.apartmentDetails.getNbOfHousing() < 25;
  }

  private boolean isVerySmallHousing() {
    return this.apartmentDetails.getNbOfHousing() <= 3;
  }

  public QuoteOffer makeOffer(OfferCalculator offerCalculator, MessageService messageService, Clock clock) {
    QuoteOffer offer = offerCalculator.calculateQuoteOffer(this, clock);

    if (this.getIdentity().email != null) {
      messageService.sendAcceptedQuoteMessage(offer);
    }

    return offer;

  }

}