package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.Offer;

public class OfferAssembler {

  public OfferResponseDTO create(Offer offer) {

    OfferResponseDTO quoteOfferResponseDTO = new OfferResponseDTO();
    quoteOfferResponseDTO.offerId = offer.getOfferId().value;
    quoteOfferResponseDTO.price = offer.getValue().amount;

    return quoteOfferResponseDTO;
  }

}


