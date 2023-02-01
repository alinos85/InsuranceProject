package ca.ulaval.glo4003.projet.base.ws.infrastructure.message;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailRenewalEffectiveDate;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EmailRenewalEffectiveDateTest {

  @Mock
  Money money;
  InsurancePolicyId insurancePolicyId;
  private String EMAIL = "EMAIL@test.com";
  private String NAME = "name";
  private String id = "Identity";
  private BigDecimal price = new BigDecimal(500);
  private LocalDate effectiveDate = LocalDate.now();
  private Identity identity;

  @Mock
  private Quote mockQuote;
  @Mock
  private InsurancePolicy insurancePolicyMock;
  @Mock
  private Messageable messageable;

  @InjectMocks
  private EmailRenewalEffectiveDate emailRenewalEffectiveDate;

  @Before
  public void setUp() {
    initMocks(this);

    insurancePolicyId = new InsurancePolicyId();
    insurancePolicyId.value = "test";

    identity = new Identity();
    identity.email = new EmailAddress(EMAIL);
    identity.name = NAME;

    willReturn(identity).given(mockQuote).getIdentity();

    willReturn(money).given(insurancePolicyMock).getPrice();
    willReturn(insurancePolicyId).given(insurancePolicyMock).getId();
    willReturn(effectiveDate).given(insurancePolicyMock).getExpirationDate();
    willReturn(identity).given(insurancePolicyMock).getIdentity();
    willReturn(effectiveDate).given(insurancePolicyMock).getEffectiveDate();

  }

  @Test
  public void givenProcessToSendEmailToAUser_whenRenewalInsuranceStart_thenReturnTheIdentityOfThePerson() {
    Assert.assertEquals(EMAIL, emailRenewalEffectiveDate.getMessageable().getEmailAddress().value);
  }

  @Test
  public void givenProcessToSendEmailWithSubject_whenRenewalInsuranceStart_thenSendMessageWithSpecificSubject() {
    Assert.assertEquals(emailRenewalEffectiveDate.getSubject(), "Renewal Insurance Policy");
  }

  @Test
  public void givenProcessToSendMessageForEmail_whenRenewalContract_thenEmailContainUserInformation() {
    String message = emailRenewalEffectiveDate.getBody();
    Assert.assertTrue(message.contains(NAME));
  }

}
