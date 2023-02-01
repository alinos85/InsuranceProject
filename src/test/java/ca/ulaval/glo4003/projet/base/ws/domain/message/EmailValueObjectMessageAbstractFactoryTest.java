package ca.ulaval.glo4003.projet.base.ws.domain.message;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Message;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailAcceptQuoteMessage;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailClaimExpire;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailMessageAbstractFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class EmailValueObjectMessageAbstractFactoryTest {

  @Mock
  QuoteOffer quoteOffer;
  private EmailMessageAbstractFactory emailMessageFactory;
  @Mock
  private Quote quote;
  @Mock
  private InsurancePolicy insurancePolicy;
  @Mock
  private Messageable messageable;
  @Mock
  private Claim claim;

  @Before
  public void setUp() {
    emailMessageFactory = new EmailMessageAbstractFactory();
  }

  @Test
  public void givenCreationOfQuote_whenQuoteHasEmail_thenMessageAccepteQuoteCreated() {
    Message message = emailMessageFactory.createAcceptQuoteMessage(quoteOffer);
    Assert.assertTrue(message instanceof EmailAcceptQuoteMessage);
  }

  @Test
  public void givenClaimWithoutSPVQNumber_whenClaimExpireDuTo5Days_thenMessageClaimExpireCreated() {
    Message message = emailMessageFactory.createClaimExpireMessage(claim, messageable);
    Assert.assertTrue(message instanceof EmailClaimExpire);
  }

}
