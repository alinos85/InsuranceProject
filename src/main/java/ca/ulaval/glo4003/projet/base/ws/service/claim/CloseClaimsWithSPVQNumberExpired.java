package ca.ulaval.glo4003.projet.base.ws.service.claim;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;

public class CloseClaimsWithSPVQNumberExpired {

  private UserRepository userRepository;
  private MessageService messageService;

  public CloseClaimsWithSPVQNumberExpired(UserRepository userRepository, MessageService messageService) {
    this.userRepository = userRepository;
    this.messageService = messageService;
  }

  public void closeReclamationTask() {
    for (User user : this.userRepository.findAllStudents()) {
      ((Student) user).closeClaimsWithoutSpvqNumberSuppliedBeforeDeadline(messageService);
      this.userRepository.save(user);
    }
  }
}
