package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.Message;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;

public class EmailAcceptQuoteMessage extends EmailMecanism implements Message {

  private QuoteOffer quoteOffer;

  public EmailAcceptQuoteMessage(QuoteOffer quoteOffer) {
    this.quoteOffer = quoteOffer;
  }

  @Override
  public Messageable getMessageable() {
    return quoteOffer.getQuote().getIdentity();
  }

  @Override
  public String getBody() {

    String htmlString = mecanismEmail("templateAcceptQuote.html");

    htmlString = htmlString.replace("$name", quoteOffer.getQuote().getIdentity().name);
    htmlString = htmlString.replace("$price", quoteOffer.getValue().amount.toString());
    htmlString = htmlString.replace("$id", quoteOffer.getOfferId().value);

    return htmlString;

  }

  @Override
  public String getSubject() {
    return "Acceptation de votre soumission";
  }
}
