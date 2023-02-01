package ca.ulaval.glo4003.projet.base.ws.infrastructure.user;

import ca.ulaval.glo4003.projet.base.ws.domain.actuary.Actuary;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepositoryInMemory implements UserRepository {

  private Map<String, User> users = new HashMap<>();

  @Override
  public void save(User user) {
    this.users.put(user.getId(), user);
  }

  @Override
  public User findByEmail(String email) {
    User user = this.users.get(email);
    if (user == null) {
      throw new UserNotFoundException();
    }
    return user;
  }

  @Override
  public boolean userExist(String email) {
    return this.users.get(email) != null;
  }

  @Override
  public Student findStudentByEmail(String email) {
    User user = this.findByEmail(email);
    if (user instanceof Student) {
      return (Student) user;
    }
    throw new UserTypeIsNotAuthorized("insured");
  }

  @Override
  public Actuary findActuaryByEmail(String email) {
    User user = this.findByEmail(email);
    if (user instanceof Actuary) {
      return (Actuary) user;
    }
    throw new UserTypeIsNotAuthorized("actuary");
  }

  @Override
  public List<Student> findAllStudents() {
    return this.users.values().stream().filter(user -> (user instanceof Student)).map(user -> (Student) user).collect(Collectors.toList());
  }
}
