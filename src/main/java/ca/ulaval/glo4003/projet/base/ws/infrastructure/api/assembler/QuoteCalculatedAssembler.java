package ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler;

import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculated;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteCalculatedDTO;

public class QuoteCalculatedAssembler {

  public QuoteCalculated create(QuoteCalculatedDTO quoteCalculatedDTO) {
    return new QuoteCalculated(quoteCalculatedDTO.price);
  }

}
