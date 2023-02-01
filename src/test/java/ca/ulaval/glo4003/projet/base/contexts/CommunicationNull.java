package ca.ulaval.glo4003.projet.base.contexts;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.CommuncationTechnic;

public class CommunicationNull implements CommuncationTechnic {

  @Override
  public void sendEmailWithGlobalEmail(String from, String pass, String[] to, String subject, String body) {
    return;
  }
}
