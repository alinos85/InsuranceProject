package ca.ulaval.glo4003.projet.base.ws.service.offer;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.QuoteAssembler;
import java.time.Clock;

public class GenerateQuoteOffer {

  private QuoteAssembler quoteAssembler;
  private OfferCalculator offerCalculator;
  private MessageService messageService;
  private QuoteOfferRepository quoteOfferRepository;
  private OfferAssembler offerAssembler;
  private Clock clock;

  public GenerateQuoteOffer(QuoteAssembler quoteAssembler, OfferCalculator offerCalculator,
      MessageService messageService, QuoteOfferRepository quoteOfferRepository, OfferAssembler offerAssembler, Clock clock) {
    this.quoteAssembler = quoteAssembler;
    this.offerCalculator = offerCalculator;
    this.messageService = messageService;
    this.quoteOfferRepository = quoteOfferRepository;
    this.offerAssembler = offerAssembler;
    this.clock = clock;
  }

  public OfferResponseDTO generateQuoteOffer(QuoteRequestDTO quoteRequestDTO) {

    Quote quote = quoteAssembler.create(quoteRequestDTO);
    QuoteOffer quoteOffer = quote.makeOffer(offerCalculator, messageService, clock);

    quoteOfferRepository.save(quoteOffer);

    OfferResponseDTO offerResponseDTO = offerAssembler.create(quoteOffer);

    return offerResponseDTO;
  }

}
