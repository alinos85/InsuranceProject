package ca.ulaval.glo4003.projet.base.ws.domain.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.AlreadyActiveInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.AlreadyUpcomingInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.CantRenewInsurancePolicyDueToInactiveException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.CantUpdateInsurancePolicyDueToInactiveException;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InsurancePoliciesTest {

  private static final Money NEW_INSURANCE_AMOUNT = new Money(10000);
  private static final BigDecimal NEW_PUBLIC_LIABILITY_AMOUNT = new BigDecimal(2000000);
  private InsurancePolicies insurancePolicies;

  @Mock
  private InsurancePolicy activeInsurancePolicy;

  @Mock
  private InsurancePolicy upcomingInsurancePolicy;

  @Mock
  InsurancePolicy updateInsurancePolicy;

  @Mock
  private OfferCalculator offerCalculator;

  @Mock
  private UpdateInsurancePolicyOffer updateInsurancePolicyOffer;

  @Mock
  private RenewInsurancePolicyOffer renewInsurancePolicyOffer;

  @Before
  public void setUp() {
    insurancePolicies = new InsurancePolicies();
    when(activeInsurancePolicy.getEffectiveDate()).thenReturn(LocalDate.now());
    when(upcomingInsurancePolicy.getEffectiveDate()).thenReturn(LocalDate.now().plusMonths(1));
    when(updateInsurancePolicy.getEffectiveDate()).thenReturn(LocalDate.now());
    when(activeInsurancePolicy.generateUpdateInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT))
        .thenReturn(updateInsurancePolicyOffer);
    when(activeInsurancePolicy.generateRenewInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT)).thenReturn(renewInsurancePolicyOffer);
  }

  @Test
  public void givenActiveInsurancePolicy_whenUpdatingInsurancePolicy_thenExpireActiveInsurancePolicy() {
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
    insurancePolicies.updateInsurancePolicy(updateInsurancePolicy);

    assertTrue(insurancePolicies.getExpiredInsurancePolicies().contains(activeInsurancePolicy));
  }

  @Test
  public void givenActiveInsurancePolicy_whenUpdatingInsurancePolicy_thenNewInsurancePolicyIsActive() {
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
    insurancePolicies.updateInsurancePolicy(updateInsurancePolicy);

    assertTrue(insurancePolicies.getActiveInsurancePolicy() == updateInsurancePolicy);
  }

  @Test(expected = AlreadyActiveInsurancePolicyException.class)
  public void givenActiveInsurancePolicy_whenAddingInsurancePolicyEffectiveToday_thenThrowAlreadyActiveInsurancePolicyException() {
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
  }

  @Test(expected = AlreadyUpcomingInsurancePolicyException.class)
  public void givenUpcomingInsurancePolicy_whenAddingInsurancePolicyEffectiveLater_thenThrowAlreadyUpcomingInsurancePolicyException() {
    insurancePolicies.addInsurancePolicy(upcomingInsurancePolicy);
    insurancePolicies.addInsurancePolicy(upcomingInsurancePolicy);
  }

  @Test
  public void givenNoActiveInsurancePolicy_whenAddingInsurancePolicyEffectiveToday_thenBecomeActiveInsurancePolicy() {
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
    assertEquals(insurancePolicies.getActiveInsurancePolicy(), activeInsurancePolicy);
  }

  @Test
  public void givenNoUpcomingInsurancePolicy_whenAddingInsurancePolicyEffectiveLater_thenBecomeUpcomingInsurancePolicy() {
    insurancePolicies.addInsurancePolicy(upcomingInsurancePolicy);
    assertEquals(insurancePolicies.getUpcomingInsurancePolicy(), upcomingInsurancePolicy);
  }

  @Test(expected = CantUpdateInsurancePolicyDueToInactiveException.class)
  public void givenNoActiveInsurancePolicy_whenGeneratingUpdateInsurancePolicyOffer_thenThrowCantUpdateInsurancePolicyDueToInactiveException() {
    insurancePolicies.generateUpdateInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT);
  }

  @Test
  public void givenActiveInsurancePolicy_whenGeneratingUpdateInsurancePolicyOffer_thenReturnUpdateInsurancePolicyOffer() {
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
    UpdateInsurancePolicyOffer generatedUpdateInsurancePolicyOffer = insurancePolicies
        .generateUpdateInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT);

    assertEquals(updateInsurancePolicyOffer, generatedUpdateInsurancePolicyOffer);
  }

  @Test(expected = CantRenewInsurancePolicyDueToInactiveException.class)
  public void givenNoActiveInsurancePolicy_whenGeneratingRenewInsurancePolicyOffer_thenThrowCantUpdateInsurancePolicyDueToInactiveException() {
    insurancePolicies.generateRenewInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT);
  }

  @Test
  public void givenActiveInsurancePolicy_whenGeneratingRenewInsurancePolicyOffer_thenReturnUpdateInsurancePolicyOffer() {
    insurancePolicies.addInsurancePolicy(activeInsurancePolicy);
    RenewInsurancePolicyOffer generatedRenewInsurancePolicyOffer = insurancePolicies.generateRenewInsurancePolicyOffer(offerCalculator, NEW_INSURANCE_AMOUNT);

    assertEquals(renewInsurancePolicyOffer, generatedRenewInsurancePolicyOffer);
  }

}