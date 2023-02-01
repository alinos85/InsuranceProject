package ca.ulaval.glo4003.projet.base.ws.domain.Quote;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;
import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;
import ca.ulaval.glo4003.projet.base.ws.domain.apartment.details.ApartmentDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.exception.InvalidUpdatingPublicLiabilityAmountException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.exception.InvalidEffectiveDateException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.exception.InvalidInitialPublicLiabilityException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class QuoteTest {

  private int AMOUNT_2000000 = 2000000;
  private int AMOUNT_1000000 = 1000000;
  private int FAKE_AMOUNT = 3000000;
  private int LARGE_HOUSING_AMOUNT = 60;
  private int VERY_SMAll_HOUSING_AMOUNT = 1;
  private int SMALL_HOUSING_AMOUNT = 20;
  private int MEDIUM_HOUSING_AMOUNT = 27;

  private Quote quote;

  @Mock
  private Identity identity;

  @Mock
  private QuoteOffer quoteOffer;

  @Mock
  private OfferCalculator offerCalculator;

  private MessageService messageService;

  private Address address;

  @Mock
  private ApartmentDetails apartmentDetails;

  private List<Animal> animals;

  @Mock
  private Money insuranceAmount;

  @Mock
  private Money publicLiabilityAmount;

  private LocalDate wrightEffectiveDate = LocalDate.of(2020, 12, 12);
  private LocalDate wrongEffectiveDate = LocalDate.of(2000, 12, 12);

  private java.time.Clock clock;

  @Before
  public void setUp() {
    initMocks(this);

    willReturn(quoteOffer).given(offerCalculator).calculateQuoteOffer(any(), any());
  }

  @Test
  public void givenOfferCalculator_whenMakingOffer_thenMakeOffer() {
    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount);

    QuoteOffer makeOffer = quote.makeOffer(offerCalculator, messageService, clock);

    Assert.assertEquals(makeOffer, quoteOffer);
  }

  @Test
  public void givenQuote_whenCloneQuote_thenQuoteCloned() {
    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount);

    Quote quoteCloned = quote.clone();

    Assert.assertEquals(quoteCloned.getEffectiveDate(), quote.getEffectiveDate());
    Assert.assertEquals(quoteCloned.getIdentity(), quote.getIdentity());
    Assert.assertEquals(quoteCloned.getAddress(), quote.getAddress());
    Assert.assertEquals(quoteCloned.getApartmentDetails(), quote.getApartmentDetails());
    Assert.assertEquals(quoteCloned.getAnimals(), quote.getAnimals());
    Assert.assertEquals(quoteCloned.getInsuranceAmount(), quote.getInsuranceAmount());
    Assert.assertEquals(quoteCloned.getPublicLiability(), quote.getPublicLiability());
  }

  @Test(expected = InvalidEffectiveDateException.class)
  public void givenInvalidEffectiveDate_whenCreatQuote_thenThrowInvalidEffectiveDateException() {
    quote = new Quote(wrongEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount);
  }

  @Test(expected = InvalidUpdatingPublicLiabilityAmountException.class)
  public void givenInvalidUpdatingPublicLiabilityVerySmallHousingAmount_whenCreatQuote_thenThrowInvalidUpdatingPublicLiabilityAmountException() {
    apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(VERY_SMAll_HOUSING_AMOUNT);
    publicLiabilityAmount = new Money(new BigDecimal(AMOUNT_2000000));

    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount, publicLiabilityAmount);
  }

  @Test(expected = InvalidUpdatingPublicLiabilityAmountException.class)
  public void givenInvalidUpdatingPublicLiabilityLargeHousingAmount_whenCreatQuote_thenThrowInvalidUpdatingPublicLiabilityAmountException() {
    apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(LARGE_HOUSING_AMOUNT);
    publicLiabilityAmount = new Money(new BigDecimal(AMOUNT_2000000));
    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount, publicLiabilityAmount);
  }

  @Test
  public void givenInValidUpdatingPublicLiabilityMediumHousingAmount_whenCreatQuote_thenThrowInvalidUpdatingPublicLiabilityAmountException() {
    apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(MEDIUM_HOUSING_AMOUNT);
    publicLiabilityAmount = new Money(new BigDecimal(AMOUNT_1000000));

    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount, publicLiabilityAmount);
    Assert.assertEquals(quote.getPublicLiability().getAmount().amount, publicLiabilityAmount.amount);
  }

  @Test(expected = InvalidInitialPublicLiabilityException.class)
  public void givenValidUpdatingPublicLiabilityMediumHousingAmount_whenCreatQuote_thenThrowInvalidUpdatingPublicLiabilityAmountException() {
    apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(MEDIUM_HOUSING_AMOUNT);
    publicLiabilityAmount = new Money(new BigDecimal(FAKE_AMOUNT));

    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount, publicLiabilityAmount);
  }

  @Test(expected = InvalidInitialPublicLiabilityException.class)
  public void givenInvalidUpdatingPublicLiabilitySmallHousingAmount_whenCreatQuote_thenThrowInvalidUpdatingPublicLiabilityAmountException() {
    apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(SMALL_HOUSING_AMOUNT);
    publicLiabilityAmount = new Money(new BigDecimal(AMOUNT_1000000));

    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount, publicLiabilityAmount);
  }

  @Test
  public void givenValidUpdatingPublicLiabilitySmallHousingAmount_whenCreatQuote_thenThrowInvalidUpdatingPublicLiabilityAmountException() {
    apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(SMALL_HOUSING_AMOUNT);
    publicLiabilityAmount = new Money(new BigDecimal(AMOUNT_2000000));

    quote = new Quote(wrightEffectiveDate, identity, address, apartmentDetails, animals, insuranceAmount, publicLiabilityAmount);

    Assert.assertEquals(quote.getPublicLiability().getAmount().amount, publicLiabilityAmount.amount);
  }
}