package ca.ulaval.glo4003.projet.base.ws.domain.communication;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;

public class MessageService {

  private MessageSender messageSender;
  private MessageAbstractFactory messageAbstractFactory;

  public MessageService(MessageSender messageSender, MessageAbstractFactory messageAbstractFactory) {
    this.messageSender = messageSender;
    this.messageAbstractFactory = messageAbstractFactory;
  }

  public void sendAcceptedQuoteMessage(QuoteOffer quoteOffer) {

    Message message = messageAbstractFactory.createAcceptQuoteMessage(quoteOffer);
    messageSender.sendMessage(message);
  }

  public void sendClaimExpireMessage(Claim claim, Messageable user) {
    Message message = messageAbstractFactory.createClaimExpireMessage(claim, user);
    messageSender.sendMessage(message);
  }

  public void sendEffectiveRenewalQuoteMessage(InsurancePolicy offer) {
    Message message = messageAbstractFactory.createEffectiveRenewalMessage(offer);
    messageSender.sendMessage(message);
  }

}
