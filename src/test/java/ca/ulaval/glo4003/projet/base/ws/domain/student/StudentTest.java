package ca.ulaval.glo4003.projet.base.ws.domain.student;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.Offer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferNotFoundExeption;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentTest {

  private static final EmailAddress USER_EMAIL = new EmailAddress("test@email.com");
  private static final String PASSWORD = "PASSWORD";
  private static final OfferId OFFER_ID = new OfferId();
  private static final Money NEW_INSURANCE_AMOUNT = new Money(15000);
  private static final BigDecimal NEW_PUBLIC_LIABILITY_AMOUNT = new BigDecimal(2000000);
  private static final long MAX_DAYS_FROM_EXPIRATION_FOR_RENEWAL = 30;

  private Student student;

  @Mock
  QuoteOffer quoteOffer;

  @Mock
  Offer offer;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private OfferCalculator offerCalculator;

  @Mock
  private RenewInsurancePolicyOffer renewInsurancePolicyOffer;

  @Mock
  private UpdateInsurancePolicyOffer updateInsurancePolicyOffer;

  @Before
  public void setUp() {
    student = new Student(USER_EMAIL, PASSWORD);

    when(quoteOffer.accept(student)).thenReturn(insurancePolicy);
    when(offer.accept(student)).thenReturn(insurancePolicy);
    when(offer.getOfferId()).thenReturn(OFFER_ID);
    when(renewInsurancePolicyOffer.getOfferId()).thenReturn(OFFER_ID);
    when(updateInsurancePolicyOffer.getOfferId()).thenReturn(OFFER_ID);
    when(insurancePolicy.generateUpdateInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT))
        .thenReturn(updateInsurancePolicyOffer);
    when(insurancePolicy.generateRenewInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT)).thenReturn(renewInsurancePolicyOffer);
  }

  @Test
  public void givenQuoteOffer_whenAcceptingQuoteOffer_thenReturnGeneratedInsurancePolicy() {
    InsurancePolicy generatedInsurancePolicy = student.acceptQuoteOffer(quoteOffer);

    assertEquals(insurancePolicy, generatedInsurancePolicy);
  }

  @Test
  public void givenHasOffer_whenAcceptingOffer_thenReturnGeneratedInsurancePolicy() {
    student.addOffer(offer);
    InsurancePolicy generatedInsurancePolicy = student.acceptOffer(offer.getOfferId());

    assertEquals(insurancePolicy, generatedInsurancePolicy);
  }

  @Test(expected = OfferNotFoundExeption.class)
  public void givenDoesntHaveOffer_whenAcceptingOffer_thenThrowOfferNotFoundExeption() {

    InsurancePolicy generatedInsurancePolicy = student.acceptOffer(offer.getOfferId());

    assertEquals(insurancePolicy, generatedInsurancePolicy);
  }

  @Test
  public void givenNewInsuranceAmount_whenRequestingRenewal_thenReturnGeneratedOffer() {
    when(insurancePolicy.getEffectiveDate()).thenReturn(LocalDate.now());
    when(insurancePolicy.getExpirationDate()).thenReturn(LocalDate.now().plusDays(MAX_DAYS_FROM_EXPIRATION_FOR_RENEWAL));

    student.addInsurancePolicy(insurancePolicy);
    RenewInsurancePolicyOffer generatedOffer = student.requestRenewInsurancePolicy(offerCalculator, NEW_INSURANCE_AMOUNT);

    assertEquals(renewInsurancePolicyOffer, generatedOffer);
  }

  @Test
  public void givenUpdateInsurancePolicyData_whenRequestingUpdate_thenReturnGeneratedOffer() {
    when(insurancePolicy.getEffectiveDate()).thenReturn(LocalDate.now());
    student.addInsurancePolicy(insurancePolicy);

    UpdateInsurancePolicyOffer generatedOffer = student.updateInsurancePolicy(offerCalculator, NEW_INSURANCE_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT);

    assertEquals(updateInsurancePolicyOffer, generatedOffer);
  }

}