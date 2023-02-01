package ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;
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
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InsurancePolicyTest {

  private static final LocalDate EFFECTIVE_DATE = LocalDate.now();
  private static final LocalDate EXPIRATION_DATE = LocalDate.now().plusYears(1);
  private static final LocalDate RENEWABLE_EXPIRATION_DATE = LocalDate.now().plusDays(30);
  private static final Money INSURANCE_AMOUNT = new Money(10000);
  private static final Money NEW_INSURANCE_AMOUNT = new Money(15000);
  private static final PublicLiability PUBLIC_LIABILITY_AMOUNT = new PublicLiability(1000000);
  private static final BigDecimal NEW_PUBLIC_LIABILITY_AMOUNT = new BigDecimal(2000000);
  private static final Money INSURANCE_PRICE = new Money(200);

  private InsurancePolicy insurancePolicy;
  private InsurancePolicy renewableInsurancePolicy;

  @Mock
  private OfferCalculator offerCalculator;

  @Mock
  private UpdateInsurancePolicyOffer updateInsurancePolicyOffer;

  @Mock
  private RenewInsurancePolicyOffer renewInsurancePolicyOffer;

  @Before
  public void setUp() {
    insurancePolicy = new InsurancePolicy(
        EFFECTIVE_DATE,
        EXPIRATION_DATE,
        new Identity(),
        new Address(),
        new ApartmentDetails(),
        new ArrayList<>(),
        INSURANCE_AMOUNT,
        PUBLIC_LIABILITY_AMOUNT,
        INSURANCE_PRICE
    );

    renewableInsurancePolicy = new InsurancePolicy(
        EFFECTIVE_DATE,
        RENEWABLE_EXPIRATION_DATE,
        new Identity(),
        new Address(),
        new ApartmentDetails(),
        new ArrayList<>(),
        INSURANCE_AMOUNT,
        PUBLIC_LIABILITY_AMOUNT,
        INSURANCE_PRICE
    );

    when(offerCalculator.calculatedUpdateInsurancePolicy(insurancePolicy, NEW_INSURANCE_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT))
        .thenReturn(updateInsurancePolicyOffer);
    when(offerCalculator.calculatedRenewalInsurancePolicy(insurancePolicy, NEW_INSURANCE_AMOUNT)).thenReturn(renewInsurancePolicyOffer);
  }

  @Test(expected = InvalidInsuranceAmountWhenUpdatingPolicyException.class)
  public void givenNewInsuranceAmountSmaller_whenGenerateUpdateInsurancePolicyOffer_throwException() {
    Money smallerNewInsurancreAmount = new Money(INSURANCE_AMOUNT.amount.doubleValue() - 5);

    insurancePolicy.generateUpdateInsurancePolicyOffer(
        offerCalculator,
        smallerNewInsurancreAmount, PUBLIC_LIABILITY_AMOUNT.amount.amount
    );
  }

  @Test(expected = InvalidPublicLiabilityWhenUpdatingPolicyException.class)
  public void givenInvalidNewPublicLiabilityAmount_whenGenerateUpdateInsurancePolicyOffer_throwException() {

    BigDecimal invalidNewPublicLiabilityAmount = new BigDecimal(1000000);

    insurancePolicy.generateUpdateInsurancePolicyOffer(
        offerCalculator,
        NEW_INSURANCE_AMOUNT,
        invalidNewPublicLiabilityAmount
    );
  }

  @Test(expected = InvalidPublicLiabilityWhenUpdatingPolicyException.class)
  public void givenValidUpdateParameters_whenGenerateUpdateInsurancePolicyOffer_thenGenerateOffer() {

    BigDecimal invalidNewPublicLiabilityAmount = new BigDecimal(1000000);

    UpdateInsurancePolicyOffer generatedUpdateInsurancePolicyOffer = insurancePolicy.generateUpdateInsurancePolicyOffer(
        offerCalculator,
        NEW_INSURANCE_AMOUNT,
        invalidNewPublicLiabilityAmount
    );

    assertEquals(updateInsurancePolicyOffer, generatedUpdateInsurancePolicyOffer);
  }

  @Test(expected = CantRenewInsurancePolicyDueToMore30DaysBeforeEndException.class)
  public void givenExpirationDateTooFar_whenGenerateRenewInsurancePolicyOffer_throwException() {

    insurancePolicy.generateRenewInsurancePolicyOffer(
        offerCalculator,
        NEW_INSURANCE_AMOUNT
    );
  }

  @Test(expected = CantRenewInsurancePolicyDueToMore30DaysBeforeEndException.class)
  public void givenRenewableInsurancePolicy_whenGenerateRenewInsurancePolicyOffer_thenGenerateOffer() {

    RenewInsurancePolicyOffer generatedRenewInsurancePolicyOffer = insurancePolicy.generateRenewInsurancePolicyOffer(
        offerCalculator,
        NEW_INSURANCE_AMOUNT
    );

    assertEquals(renewInsurancePolicyOffer, generatedRenewInsurancePolicyOffer);
  }
}