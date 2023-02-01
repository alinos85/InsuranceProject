package ca.ulaval.glo4003.projet.base.ws.service.user;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SigninRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.actuary.Actuary;
import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.UserApiException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.WrongPasswordException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.user.assembler.UserAssembler;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class UserServiceTest {

  private String EMAIL = "A@A.A";
  private String PASSWORD = "A";
  private SignupRequestDTO signupRequestDTO;
  private SigninRequestDTO signinRequestDTO;
  private AuthentificationToken authentificationToken = new AuthentificationToken("token");

  @Mock
  private Student student;
  @Mock
  private Actuary actuary;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserAssembler userAssembler;
  @Mock
  private MessageService messageService;
  @Mock
  private AuthentificationService authentificationService;

  private UserService userService;

  @Before
  public void setUp() {
    initMocks(this);

    setUpSignUpRequestDTO();
    setUpSignInRequestDTO();
    setUpSignUp();
    setUpFindByEmail();
    setUpFindAllStudent();

    userService = new UserService(userRepository, userAssembler, messageService, authentificationService);
  }

  private void setUpSignInRequestDTO() {
    signinRequestDTO = new SigninRequestDTO();
    signinRequestDTO.password = PASSWORD;
    signinRequestDTO.email = EMAIL;
  }

  private void setUpFindByEmail() {
    willReturn(student).given(userRepository).findByEmail(EMAIL);
  }

  private void setUpSignUp() {
    willReturn(student).given(userAssembler).createStudent(signupRequestDTO);
    willReturn(actuary).given(userAssembler).createActuary(signupRequestDTO);
    willReturn(authentificationToken).given(authentificationService).addTokenFromUser(student);
    willReturn(authentificationToken).given(authentificationService).addTokenFromUser(actuary);
  }

  private void setUpFindAllStudent() {
    List<Student> students = new ArrayList<>();
    students.add(student);
    willReturn(students).given(userRepository).findAllStudents();
  }

  private void setUpSignUpRequestDTO() {
    signupRequestDTO = new SignupRequestDTO();
    signupRequestDTO.email = EMAIL;
    signupRequestDTO.password = PASSWORD;
  }

  @Test
  public void givenSignUpDTO_whenCreatingStudent_thenReturnAndSaveStudent() {
    AuthentificationToken authentificationToken = userService.createStudent(signupRequestDTO);

    verify(userAssembler).createStudent(signupRequestDTO);
    verify(userRepository).save(student);
    assertNotNull(authentificationToken);
  }

  @Test(expected = UserAlreadyExistException.class)
  public void givenAlreadyExistingEmail_whenCreatingStudent_thenThrowAlreadyExist() {
    willReturn(true).given(userRepository).userExist(signinRequestDTO.email);

    userService.createStudent(signupRequestDTO);
  }

  @Test(expected = UserAlreadyExistException.class)
  public void givenAlreadyExistingEmail_whenCreatingActuary_thenThrowAlreadyExistException() {
    willReturn(true).given(userRepository).userExist(signinRequestDTO.email);

    userService.createActuary(signupRequestDTO);
  }

  @Test
  public void givenSignUpDTO_whenCreatingActuary_thenReturnAndSaveStudent() {
    AuthentificationToken authentificationToken = userService.createActuary(signupRequestDTO);

    verify(userAssembler).createActuary(signupRequestDTO);
    verify(userRepository).save(actuary);
    assertNotNull(authentificationToken);
  }

  @Test(expected = UserApiException.class)
  public void givenSigninInvalidEmail_whenSignIn_thenThrowUserApiException() {
    signinRequestDTO.email = "WRONG_EMAIL";

    userService.signin(signinRequestDTO);
  }

  @Test(expected = WrongPasswordException.class)
  public void givenSigninDTOInvalidPassword_whenSignIn_thenThrowWrongPasswordException() {
    signupRequestDTO.password = "WRONG_PASSWORD";
    willReturn(PASSWORD).given(student).getHashedPassword();

    userService.signin(signinRequestDTO);
  }

  @Test
  public void whenUpdatingPolicies_thenShouldUpdateStudents() {
    userService.updateInsurancePolicies();

    verify(userRepository).findAllStudents();
    verify(student).updateInsurancePolicies(messageService);
  }
}