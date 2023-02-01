package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.NoChangeRequestedInUpdateException;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateInsurancePolicyServiceTest {

  private static final String USER_EMAIL = "test@email.com";
  private static final BigDecimal NEW_INSURANCE_POLICY = new BigDecimal(10000);
  private static final BigDecimal NEW_PUBLIC_LIABILITY = new BigDecimal(2000000);
  private static final OfferId OFFER_ID = new OfferId();

  private UpdateInsurancePolicyService updateInsurancePolicyService;

  @Mock
  private OfferAssembler offerAssembler;

  @Mock
  private UserRepository userRepository;

  @Mock
  private OfferCalculator offerCalculator;

  @Mock
  private Student student;

  @Mock
  private UpdateInsurancePolicyOffer updateInsurancePolicyOffer;

  @Mock
  private OfferResponseDTO offerResponseDTO;

  @Before
  public void setUp() {
    updateInsurancePolicyService = new UpdateInsurancePolicyService(userRepository, offerCalculator, offerAssembler);

    when(userRepository.findStudentByEmail(USER_EMAIL)).thenReturn(student);
    when(student.updateInsurancePolicy(eq(offerCalculator), any(Money.class), any(BigDecimal.class))).thenReturn(updateInsurancePolicyOffer);
    when(updateInsurancePolicyOffer.getOfferId()).thenReturn(OFFER_ID);
    when(offerAssembler.create(updateInsurancePolicyOffer)).thenReturn(offerResponseDTO);
  }

  @Test(expected = NoChangeRequestedInUpdateException.class)
  public void givenNewInsuranceAmountAndNewPublicLiabilityAmountNull_whenGeneratingOffer_throwException() {
    updateInsurancePolicyService.updateInsurancePolicyRequest(USER_EMAIL, null, null);
  }

  @Test
  public void givenValidData_whenGeneratingOffer_thenSaveStudent() {
    updateInsurancePolicyService.updateInsurancePolicyRequest(USER_EMAIL, NEW_INSURANCE_POLICY, NEW_PUBLIC_LIABILITY);

    verify(userRepository).save(student);
  }

  @Test
  public void givenValidData_whenGeneratingOffer_thenReturnGeneratedOfferResponseDTO() {
    OfferResponseDTO offerResponseDTO = updateInsurancePolicyService.updateInsurancePolicyRequest(USER_EMAIL, NEW_INSURANCE_POLICY, NEW_PUBLIC_LIABILITY);

    assertEquals(this.offerResponseDTO, offerResponseDTO);
  }

}