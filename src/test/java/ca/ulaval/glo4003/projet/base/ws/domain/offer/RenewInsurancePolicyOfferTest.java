package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferAlreadyAcceptedException;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferExpiredException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RenewInsurancePolicyOfferTest {

  private static final InsurancePolicyId OLD_INSURANCE_POLICY_ID = new InsurancePolicyId();
  private static final Money OFFER_VALUE = new Money(200);

  public RenewInsurancePolicyOffer renewInsurancePolicyOffer;

  @Mock
  private Student student;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private InsurancePolicy oldInsurancePolicy;

  @Before
  public void setUp() {
    renewInsurancePolicyOffer = new RenewInsurancePolicyOffer(OFFER_VALUE, oldInsurancePolicy);

    when(oldInsurancePolicy.getId()).thenReturn(OLD_INSURANCE_POLICY_ID);
    when(oldInsurancePolicy.getExpirationDate()).thenReturn(LocalDate.now());
    when(student.isInsurancePolicyActive(OLD_INSURANCE_POLICY_ID)).thenReturn(true);

  }

  @Test(expected = OfferAlreadyAcceptedException.class)
  public void givenAlreadyAccepted_whenAccepted_thenThrowOfferAlreadyAcceptedException() {

    //First accept ok
    renewInsurancePolicyOffer.accept(student);

    renewInsurancePolicyOffer.accept(student);
  }

  @Test(expected = OfferExpiredException.class)
  public void givenStudentActiveInsurancePolicyDifferFromOldInsurancePolicy_whenAccepted_thenThrowOfferExpiredException() {

    when(student.isInsurancePolicyActive(OLD_INSURANCE_POLICY_ID)).thenReturn(false);
    renewInsurancePolicyOffer.accept(student);
  }

  @Test
  public void givenValidRenewInsurancePolicyOffer_whenAccepted_thenAddGeneratedInsurancePolicyToStudent() {

    InsurancePolicy generatedInsursncePolicy = renewInsurancePolicyOffer.accept(student);

    verify(student).addInsurancePolicy(generatedInsursncePolicy);
  }

}