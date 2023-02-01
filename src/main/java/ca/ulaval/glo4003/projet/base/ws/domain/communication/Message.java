package ca.ulaval.glo4003.projet.base.ws.domain.communication;

public interface Message {

  Messageable getMessageable();

  String getBody();

  String getSubject();

}
