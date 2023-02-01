package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailAcceptQuoteMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SendMessageSenderSmtpTest {

  @Mock
  private EmailSender emailSender;

  @Mock
  private Messageable messageable;

  private EmailAddress email;

  private Identity identity;

  private String message;

  private String id;
  @Mock
  private EmailAcceptQuoteMessage messageAcceptationQuote;
  @Mock
  private Quote quote;

  @InjectMocks
  private EmailMessageSender emailCommunication;

  @Before
  public void setUp() {
    initMocks(this);
    email = new EmailAddress("xrrgeo23@gmail.com");
    message = "";

    willReturn(messageable).given(messageAcceptationQuote).getMessageable();
    willReturn(message).given(messageAcceptationQuote).getBody();
    willReturn("Soumission").given(messageAcceptationQuote).getSubject();
  }

  @Test
  public void givenCommunicationByEmail_whenAQuoteIsSubmit_thenEmailSendMessageMadeBy() {
    when(messageAcceptationQuote.getMessageable().getEmailAddress()).thenReturn(email);
    identity = new Identity();
    identity.email = email;
    when(messageAcceptationQuote.getBody()).thenReturn(message);
    when(messageAcceptationQuote.getSubject()).thenReturn("Soumission");

    emailCommunication.sendMessage(messageAcceptationQuote);
    verify(emailSender).sendEmail(email, message, "Soumission");
  }
}
