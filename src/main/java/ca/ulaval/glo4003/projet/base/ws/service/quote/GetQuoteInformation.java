package ca.ulaval.glo4003.projet.base.ws.service.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.InformationAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.InformationsDTO;
import java.time.Clock;

public class GetQuoteInformation {

  private QuoteOfferRepository quoteOfferRepository;
  private InformationAssembler informationAssembler;
  private OfferCalculator offerCalculator;
  private MessageService messageService;
  private Clock clock;

  public GetQuoteInformation(QuoteOfferRepository quoteOfferRepository,

      InformationAssembler informationAssembler, OfferCalculator offerCalculator,
      MessageService messageService, Clock clock) {

    this.quoteOfferRepository = quoteOfferRepository;
    this.informationAssembler = informationAssembler;
    this.offerCalculator = offerCalculator;
    this.messageService = messageService;
    this.clock = clock;
  }

  public InformationsDTO findQuote(String id) {
    Quote quote = quoteOfferRepository.findById(id).getQuote();
    QuoteOffer quoteOffer = quote.makeOffer(offerCalculator, messageService, clock);

    return informationAssembler.create(quote, quoteOffer);
  }
}
