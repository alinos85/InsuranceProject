package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import java.util.UUID;

public class OfferId {

  public String value;

  public OfferId() {
    this.value = UUID.randomUUID().toString();
  }

  public OfferId(String quoteOfferId) {
    this.value = UUID.fromString(quoteOfferId).toString();
  }
}
