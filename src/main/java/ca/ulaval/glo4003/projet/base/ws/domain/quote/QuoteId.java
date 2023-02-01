package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import java.util.UUID;

public class QuoteId {

  public UUID value;

  public QuoteId() {
    this.value = UUID.randomUUID();
  }
}
