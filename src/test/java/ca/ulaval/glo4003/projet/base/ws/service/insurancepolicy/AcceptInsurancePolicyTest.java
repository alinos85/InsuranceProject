package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AcceptInsurancePolicyTest {

  private String EMAIL = "EMAIL";
  private String OFFER_ID = UUID.randomUUID().toString();

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private Student student;

  @Mock
  private QuoteOffer quoteOffer;

  @Mock
  private UserRepository userRepository;

  @Mock
  private QuoteOfferRepository quoteOfferRepository;

  @Mock
  private InsurancePolicyAssembler insurancePolicyAssembler;

  private AcceptInsurancePolicy acceptInsurancePolicy;

  @Before
  public void setUp() {
    initMocks(this);

    OfferId offerId = new OfferId();

    acceptInsurancePolicy = new AcceptInsurancePolicy(userRepository, quoteOfferRepository, insurancePolicyAssembler);
    willReturn(student).given(userRepository).findStudentByEmail(EMAIL);
    willReturn(quoteOffer).given(quoteOfferRepository).findById(any(OfferId.class));
    willReturn(insurancePolicy).given(student).acceptQuoteOffer(quoteOffer);
    willReturn(offerId).given(quoteOffer).getOfferId();
  }

  @Test
  public void givenEmailAndOfferId_whenAcceptingOffer_thenAcceptOffer() {
    acceptInsurancePolicy.acceptOffer(EMAIL, OFFER_ID);

    verify(student).acceptQuoteOffer(quoteOffer);
  }
}