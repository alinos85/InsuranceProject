package ca.ulaval.glo4003.projet.base.ws.infrastructure.message;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailAcceptQuoteMessage;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EmailValueObjectAcceptQuoteMessageTest {

  @Mock
  Quote quote;
  @Mock
  QuoteOffer quoteOffer;
  private String EMAIL = "EMAIL@test.com";
  private String NAME = "name";
  private String id = "Identity";
  private BigDecimal price = new BigDecimal(500);
  private Money money = new Money(500);
  private InsurancePolicyId insurancePolicyId = new InsurancePolicyId();
  private OfferId offerId = new OfferId();
  @Mock
  private Identity identity;
  @Mock
  private Quote mockQuote;
  @Mock
  private InsurancePolicy insurancePolicyMock;

  @InjectMocks
  private EmailAcceptQuoteMessage emailAcceptQuoteMessage;

  @Before
  public void setUp() {
    initMocks(this);

    offerId.value = "test";

    identity = new Identity();
    identity.email = new EmailAddress(EMAIL);
    identity.name = NAME;

    willReturn(identity).given(mockQuote).getIdentity();
    willReturn(quote).given(quoteOffer).getQuote();
    willReturn(money).given(quoteOffer).getValue();
    willReturn(offerId).given(quoteOffer).getOfferId();
    willReturn(identity).given(quote).getIdentity();

    willReturn(money).given(insurancePolicyMock).getPrice();
    willReturn(insurancePolicyId).given(insurancePolicyMock).getId();

  }

  @Test
  public void givenProcessToSendEmailToAUser_whenSubmitForAQuote_thenReturnTheIdentityOfThePerson() {
    Assert.assertEquals(EMAIL, emailAcceptQuoteMessage.getMessageable().getEmailAddress().value);
  }

  @Test
  public void givenProcessToSendEmailWithSubject_whenSubmitForAQuote_thenSendMessageWithSpecificSubject() {
    Assert.assertEquals(emailAcceptQuoteMessage.getSubject(), "Acceptation de votre soumission");
  }

  @Test
  public void givenProcessToSendMessageForEmail_whenSubmitForAQuote_thenEmailContainUserInformation() {
    String message = emailAcceptQuoteMessage.getBody();
    Assert.assertTrue(message.contains(NAME));
    Assert.assertTrue(message.contains(price.toString()));
    Assert.assertTrue(message.contains(offerId.value));
  }

}
