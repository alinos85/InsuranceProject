package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import static org.mockito.Mockito.verify;

import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;
import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;
import ca.ulaval.glo4003.projet.base.ws.domain.apartment.details.ApartmentDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferAlreadyAcceptedException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferExpiredException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuoteOfferTest {

  private static final Money OFFER_VALUE = new Money(200);
  private static final LocalDate EXPIRED_DATE = LocalDate.now().minusDays(6);
  private static final Money INSIRANCE_AMOUNT = new Money(10000);

  public QuoteOffer quoteOffer;
  public QuoteOffer expiredQuoteOffer;

  Quote quote;

  @Mock
  private Student student;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Before
  public void setUp() {
    quote = new Quote(LocalDate.now(), new Identity(), new Address(), new ApartmentDetails(), new ArrayList<Animal>(), INSIRANCE_AMOUNT);
    quoteOffer = new QuoteOffer(quote, OFFER_VALUE, Clock.systemDefaultZone());
    expiredQuoteOffer = new QuoteOffer(quote, OFFER_VALUE, Clock.fixed(EXPIRED_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault()));
  }

  @Test(expected = OfferAlreadyAcceptedException.class)
  public void givenAlreadyAccepted_whenAccepted_thenThrowOfferAlreadyAcceptedException() {

    //First accept ok
    quoteOffer.accept(student);

    quoteOffer.accept(student);
  }

  @Test(expected = OfferExpiredException.class)
  public void givenPassedExpirationDate_whenAccepted_thenThrowOfferExpiredException() {

    expiredQuoteOffer.accept(student);
  }

  @Test
  public void givenValidQuoteOffer_whenAccepted_thenAddOfferToStudent() {

    quoteOffer.accept(student);

    verify(student).addOffer(quoteOffer);
  }

  @Test
  public void givenValidQuoteOffer_whenAccepted_thenAddGeneratedInsurancePolityToStudent() {

    InsurancePolicy insurancePolicy = quoteOffer.accept(student);

    verify(student).addInsurancePolicy(insurancePolicy);
  }
}