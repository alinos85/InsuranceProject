package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.Message;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageSender;

public class EmailMessageSender implements MessageSender {

  private EmailSender emailSender;

  public EmailMessageSender(EmailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Override
  public void sendMessage(Message message) {
    if (message.getMessageable().getEmailAddress() != null) {
      emailSender.sendEmail(message.getMessageable().getEmailAddress(), message.getBody(), message.getSubject());
    }
  }
}
