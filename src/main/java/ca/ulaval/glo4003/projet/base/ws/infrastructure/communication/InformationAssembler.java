package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication;

import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.QuoteAssembler;

public class InformationAssembler {

  private QuoteAssembler quoteAssembler;
  private OfferAssembler offerAssembler;

  public InformationAssembler(QuoteAssembler quoteAssembler, OfferAssembler offerAssembler) {
    this.quoteAssembler = quoteAssembler;
    this.offerAssembler = offerAssembler;
  }

  public InformationsDTO create(Quote quote, QuoteOffer quoteOffer) {
    InformationsDTO informationsDto = new InformationsDTO();
    informationsDto.quoteInformations = quoteAssembler.create(quote);
    informationsDto.offerResponseDTO = offerAssembler.create(quoteOffer);
    return informationsDto;
  }
}
