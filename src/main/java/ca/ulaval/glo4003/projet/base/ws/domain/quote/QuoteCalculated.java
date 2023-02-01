package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import java.math.BigDecimal;

public class QuoteCalculated {

  private BigDecimal price;

  public QuoteCalculated(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
