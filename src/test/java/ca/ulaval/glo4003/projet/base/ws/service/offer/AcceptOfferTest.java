package ca.ulaval.glo4003.projet.base.ws.service.offer;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.InsurancePolicyAssembler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AcceptOfferTest {

  private static final String USER_EMAIL = "email@test.cim";
  private static final OfferId OFFER_ID = new OfferId();
  private static final String OFFER_ID_PARAM = OFFER_ID.value;
  private static String INSURANCE_POLICY_ID = (new InsurancePolicyId()).value;

  private AcceptOffer acceptOffer;
  private InsurancePolicyAssembler insurancePolicyAssembler;

  @Mock
  private UserRepository userRepository;

  @Mock
  private Student student;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Before
  public void setUp() {

    initMocks(true);

    insurancePolicyAssembler = new InsurancePolicyAssembler();
    acceptOffer = new AcceptOffer(userRepository, insurancePolicyAssembler);

    willReturn(student).given(userRepository).findStudentByEmail(USER_EMAIL);
    when(student.acceptOffer(any(OfferId.class))).thenReturn(insurancePolicy);
    willReturn(new InsurancePolicyId(INSURANCE_POLICY_ID)).given(insurancePolicy).getId();
  }

  @Test
  public void givenUserEmailAndOfferId_whenAcceptingOffer_thenSaveStudentToRepository() {
    acceptOffer.acceptOffer(USER_EMAIL, OFFER_ID_PARAM);

    verify(userRepository).save(student);
  }

  @Test
  public void givenUserEmailAndOfferId_whenAcceptingOffer_thenReturnInsurancePolicyResponseDtoWithGoodId() {
    InsurancePolicyResponseDTO insurancePolicyResponseDTO = acceptOffer.acceptOffer(USER_EMAIL, OFFER_ID_PARAM);

    assertEquals(INSURANCE_POLICY_ID, insurancePolicyResponseDTO.insurancePolicyId);
  }
}