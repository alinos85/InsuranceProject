package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;

public class CancelUpcommingInsurancePolicy {

  private UserRepository userRepository;

  public CancelUpcommingInsurancePolicy(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void cancelUpcommingInsurancePolicy(String email) {
    Student student = this.userRepository.findStudentByEmail(email);
    student.cancelUpcomingInsurancePolicy();
  }
}
