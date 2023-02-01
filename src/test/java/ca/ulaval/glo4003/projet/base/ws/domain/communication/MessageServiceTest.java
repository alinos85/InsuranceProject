package ca.ulaval.glo4003.projet.base.ws.domain.communication;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MessageServiceTest {

  @Mock
  private Claim claim;

  @Mock
  private MessageAbstractFactory messageAbstractFactory;

  @Mock
  private MessageSender messageSender;

  @Mock
  private Messageable messageable;

  @Mock
  private InsurancePolicy insurancePolicy;

  @Mock
  private QuoteOffer quoteOffer;

  @Mock
  private Message message;

  @InjectMocks
  private MessageService messageService;

  @Before
  public void setUp() {

    initMocks(this);

    willReturn(message).given(messageAbstractFactory).createAcceptQuoteMessage(quoteOffer);
    willReturn(message).given(messageAbstractFactory).createEffectiveRenewalMessage(insurancePolicy);
    willReturn(message).given(messageAbstractFactory).createClaimExpireMessage(claim, messageable);

  }

  @Test
  public void givenQuoteOffer_whenSubmitAQuote_thenSendMessageToAcceptQuote() {

    messageService.sendAcceptedQuoteMessage(quoteOffer);

    verify(messageAbstractFactory).createAcceptQuoteMessage(quoteOffer);
    verify(messageSender).sendMessage(message);

  }

  @Test
  public void givenRenew_whenRenewInsurance_thenSendMessageApproveRenew() {

    messageService.sendEffectiveRenewalQuoteMessage(insurancePolicy);

    verify(messageAbstractFactory).createEffectiveRenewalMessage(insurancePolicy);
    verify(messageSender).sendMessage(message);

  }

  @Test
  public void givenSituation_whenInsuranceStop_thenSendMessageExpireDate() {

    messageService.sendClaimExpireMessage(claim, messageable);

    verify(messageAbstractFactory).createClaimExpireMessage(claim, messageable);
    verify(messageSender).sendMessage(message);

  }

}
