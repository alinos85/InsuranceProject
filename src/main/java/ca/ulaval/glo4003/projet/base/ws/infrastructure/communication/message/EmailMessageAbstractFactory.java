package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Message;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageAbstractFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;

public class EmailMessageAbstractFactory implements MessageAbstractFactory {

  @Override
  public Message createAcceptQuoteMessage(QuoteOffer quoteOffer) {
    return new EmailAcceptQuoteMessage(quoteOffer);
  }

  @Override
  public Message createClaimExpireMessage(Claim claim, Messageable user) {
    return new EmailClaimExpire(claim, user);
  }

  @Override
  public Message createEffectiveRenewalMessage(InsurancePolicy insurancePolicy) {
    return new EmailRenewalEffectiveDate(insurancePolicy);
  }
}
