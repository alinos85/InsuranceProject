package ca.ulaval.glo4003.projet.base.ws.service.claim;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimId;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.spvq.SpvqNumber;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;

public class AddSPVQNumberToClaim {

  private MessageService messageService;
  private UserRepository userRepository;

  public AddSPVQNumberToClaim(UserRepository userRepository, MessageService messageService) {
    this.userRepository = userRepository;
    this.messageService = messageService;
  }

  public void addSPVQNumber(String userEmail, String claimId, String spvqNumber) {

    Student student = userRepository.findStudentByEmail(userEmail);
    ClaimId id = new ClaimId(claimId);
    SpvqNumber spvq = new SpvqNumber(spvqNumber);

    student.addSPVQNumberToClaim(id, spvq);
  }
}
