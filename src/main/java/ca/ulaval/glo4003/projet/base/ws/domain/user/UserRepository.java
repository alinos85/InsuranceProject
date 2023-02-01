package ca.ulaval.glo4003.projet.base.ws.domain.user;

import ca.ulaval.glo4003.projet.base.ws.domain.actuary.Actuary;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import java.util.List;

public interface UserRepository {

  void save(User user);

  User findByEmail(String email);

  boolean userExist(String email);

  Student findStudentByEmail(String email);

  Actuary findActuaryByEmail(String email);

  List<Student> findAllStudents();
}
