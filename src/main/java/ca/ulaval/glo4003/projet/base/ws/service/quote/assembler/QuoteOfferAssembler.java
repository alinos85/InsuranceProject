package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteOfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;

public class QuoteOfferAssembler {

  public QuoteOfferResponseDTO create(QuoteOffer quoteOffer) {

    QuoteOfferResponseDTO quoteOfferResponseDTO = new QuoteOfferResponseDTO();
    quoteOfferResponseDTO.quoteOfferId = quoteOffer.getOfferId().value;
    quoteOfferResponseDTO.price = quoteOffer.getValue().amount;

    return quoteOfferResponseDTO;
  }

}


