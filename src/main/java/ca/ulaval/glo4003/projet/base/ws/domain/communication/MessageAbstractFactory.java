package ca.ulaval.glo4003.projet.base.ws.domain.communication;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;

public interface MessageAbstractFactory {

  Message createAcceptQuoteMessage(QuoteOffer quoteOffer);

  Message createClaimExpireMessage(Claim claim, Messageable user);

  Message createEffectiveRenewalMessage(InsurancePolicy offer);

}
