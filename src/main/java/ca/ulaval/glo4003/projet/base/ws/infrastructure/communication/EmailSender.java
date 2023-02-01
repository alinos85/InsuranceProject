package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication;

import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;

public class EmailSender {

  private static String USER_NAME = "habitatulavaleq7";
  private static String PASSWORD = "glo4003equipe7";
  private CommuncationTechnic communcationTechnic;

  public EmailSender(CommuncationTechnic communcationTechnic) {
    this.communcationTechnic = communcationTechnic;
  }

  public void sendEmail(EmailAddress emailTo, String body, String subject) {
    String from = USER_NAME;
    String pass = PASSWORD;
    String[] to = {emailTo.value}; // list of recipient email addresses
    communcationTechnic.sendEmailWithGlobalEmail(from, pass, to, subject, body);
  }

}