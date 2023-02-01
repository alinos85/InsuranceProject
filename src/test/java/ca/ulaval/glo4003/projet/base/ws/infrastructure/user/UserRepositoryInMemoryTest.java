package ca.ulaval.glo4003.projet.base.ws.infrastructure.user;

import static org.junit.Assert.assertEquals;

import ca.ulaval.glo4003.projet.base.ws.domain.actuary.Actuary;
import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryInMemoryTest {

  private EmailAddress STUDENT_EMAIL = new EmailAddress("Student@a.com");
  private EmailAddress ACTUARY_EMAIL = new EmailAddress("Actuary@a.com");
  private String PASSWORD = "A";
  private Student student;
  private Actuary actuary;
  private UserRepository userRepositoryInMemory;

  @Before
  public void setUp() {
    userRepositoryInMemory = new UserRepositoryInMemory();

    student = new Student(STUDENT_EMAIL, PASSWORD);
    actuary = new Actuary(ACTUARY_EMAIL, PASSWORD);
    userRepositoryInMemory.save(student);
    userRepositoryInMemory.save(actuary);
  }

  @Test
  public void givenExistingEmail_whenFindByEmail_thenReturnTheUser() {
    User user = userRepositoryInMemory.findByEmail(student.getId());

    assertEquals(user, student);
  }

  @Test(expected = UserNotFoundException.class)
  public void givenNonExistingEmail_whenFindByEmail_thenThrowUserNotFoundException() {
    userRepositoryInMemory.findByEmail("NON_EXISTING_USER_EMAIL");
  }

  @Test
  public void givenExistingStudentEmail_whenFindStudentByEmail_thenReturnTheStudent() {
    Student returnedStudent = userRepositoryInMemory.findStudentByEmail(student.getId());

    assertEquals(returnedStudent, student);
  }

  @Test(expected = UserTypeIsNotAuthorized.class)
  public void givenNonExistingStudentEmail_whenFindStudentByEmail_thenThrowUserTypeIsNotAuthorized() {
    userRepositoryInMemory.findStudentByEmail(actuary.getId());
  }

  @Test
  public void givenExistingActuaryEmail_whenFindActuaryByEmail_thenReturnTheActuary() {
    Actuary returnedActuary = userRepositoryInMemory.findActuaryByEmail(actuary.getId());

    assertEquals(returnedActuary, actuary);
  }

  @Test(expected = UserTypeIsNotAuthorized.class)
  public void givenNonExistingActuaryEmail_whenFindActuaryByEmail_thenThrowUserTypeIsNotAuthorized() {
    userRepositoryInMemory.findActuaryByEmail(student.getId());
  }

  @Test
  public void whenFindAllStudents_thenShouldReturnAllStudents() {
    List<Student> students = userRepositoryInMemory.findAllStudents();

    assertEquals(students.size(), 1);
  }
}