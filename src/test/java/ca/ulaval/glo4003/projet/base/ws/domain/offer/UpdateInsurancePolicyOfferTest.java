package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferAlreadyAcceptedException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferExpiredException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateInsurancePolicyOfferTest {

  private static final InsurancePolicyId OLD_INSURANCE_POLICY_ID = new InsurancePolicyId();
  private static final Money OFFER_VALUE = new Money(200);
  private static final Money NEW_INSURANCE_POLICY_AMOUNT = new Money(10000);
  private static final BigDecimal NEW_PUBLIC_LIABILITY_AMOUNT = new BigDecimal(2000000);

  public UpdateInsurancePolicyOffer updateInsurancePolicyOffer;

  @Mock
  private Student student;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private InsurancePolicy oldInsurancePolicy;

  @Before
  public void setUp() {
    updateInsurancePolicyOffer = new UpdateInsurancePolicyOffer(oldInsurancePolicy, OFFER_VALUE, NEW_INSURANCE_POLICY_AMOUNT, NEW_PUBLIC_LIABILITY_AMOUNT);

    when(oldInsurancePolicy.getId()).thenReturn(OLD_INSURANCE_POLICY_ID);
    when(student.isInsurancePolicyActive(OLD_INSURANCE_POLICY_ID)).thenReturn(true);
  }

  @Test(expected = OfferAlreadyAcceptedException.class)
  public void givenAlreadyAccepted_whenAccepted_thenThrowOfferAlreadyAcceptedException() {

    //First accept ok
    updateInsurancePolicyOffer.accept(student);

    updateInsurancePolicyOffer.accept(student);
  }

  @Test(expected = OfferExpiredException.class)
  public void givenStudentActiveInsurancePolicyDifferFromOldInsurancePolicy_whenAccepted_thenThrowOfferExpiredException() {

    when(student.isInsurancePolicyActive(OLD_INSURANCE_POLICY_ID)).thenReturn(false);
    updateInsurancePolicyOffer.accept(student);
  }

  @Test
  public void givenValidUpdateInsurancePolicyOffer_whenAccepted_thenUpdateStudentActivePolicyWithGeneratedInsurancePolicy() {

    InsurancePolicy generatedInsurancePolicy = updateInsurancePolicyOffer.accept(student);

    verify(student).updateInsurancePolicy(generatedInsurancePolicy);
  }

}