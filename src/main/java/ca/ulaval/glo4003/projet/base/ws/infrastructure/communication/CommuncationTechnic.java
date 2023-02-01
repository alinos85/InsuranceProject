package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication;

public interface CommuncationTechnic {

  void sendEmailWithGlobalEmail(String from, String pass, String[] to,
      String subject, String body);

}
