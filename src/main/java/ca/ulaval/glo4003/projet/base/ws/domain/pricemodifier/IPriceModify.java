package ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier;

import java.math.BigDecimal;

public interface IPriceModify {

  PriceModifierValue modifyPrice(BigDecimal price);
}
