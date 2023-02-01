package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferAlreadyAcceptedException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferExpiredException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import java.time.Clock;
import java.time.LocalDate;

public class QuoteOffer implements Offer {

  private OfferId offerId;
  private Quote quote;
  private Money value;
  private LocalDate creationDate;
  private boolean accepted;

  public QuoteOffer(Quote quote, Money value, Clock clock) {
    offerId = new OfferId();
    this.quote = quote;
    this.value = value;
    this.creationDate = LocalDate.now(clock);
    this.accepted = false;
  }

  public Quote getQuote() {
    return quote;
  }

  public Money getValue() {
    return value;
  }

  @Override
  public boolean isOfferAccepted() {
    return this.accepted;
  }

  public InsurancePolicy accept(Student student) {
    if (this.isOfferAccepted()) {
      throw new OfferAlreadyAcceptedException();
    }
    if (LocalDate.now().isAfter(creationDate.plusDays(5))) {
      throw new OfferExpiredException("Cant accept Offer after 5 or more days");
    }

    InsurancePolicy insurancePolicy = this.generateInsurancePolicy();

    student.addOffer(this);
    student.addInsurancePolicy(insurancePolicy);
    this.accepted = true;

    return insurancePolicy;
  }

  private InsurancePolicy generateInsurancePolicy() {
    LocalDate effectiveDate;
    if (this.quote.getEffectiveDate().isAfter(LocalDate.now())) {
      effectiveDate = this.getQuote().getEffectiveDate();
    } else {
      effectiveDate = LocalDate.now();
    }

    return new InsurancePolicy(
        effectiveDate,
        effectiveDate.plusYears(1),
        this.quote.getIdentity(),
        this.quote.getAddress(),
        this.quote.getApartmentDetails(),
        this.quote.getAnimals(),
        this.quote.getInsuranceAmount(),
        this.quote.getPublicLiability(),
        this.getValue()
    );

  }

  public OfferId getOfferId() {
    return offerId;
  }
}
