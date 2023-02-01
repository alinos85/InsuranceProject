package ca.ulaval.glo4003.projet.base.ws.service.user;

import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SigninRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.UserApiException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.WrongPasswordException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.user.assembler.UserAssembler;
import java.util.List;

public class UserService {

  private String USER_ALREADY_EXIST = "User already exist";

  private UserRepository userRepository;
  private UserAssembler userAssembler;
  private MessageService messageService;
  private AuthentificationService authentificationService;

  public UserService(UserRepository userRepository, UserAssembler userAssembler,
      MessageService messageService, AuthentificationService authentificationService) {
    this.userRepository = userRepository;
    this.userAssembler = userAssembler;
    this.messageService = messageService;
    this.authentificationService = authentificationService;
  }

  public AuthentificationToken createStudent(SignupRequestDTO signupRequestDTO) {
    if (userRepository.userExist(signupRequestDTO.email)) {
      throw new UserAlreadyExistException(USER_ALREADY_EXIST);
    }
    User user = this.userAssembler.createStudent(signupRequestDTO);
    this.userRepository.save(user);
    return authentificationService.addTokenFromUser(user);
  }

  public AuthentificationToken createActuary(SignupRequestDTO signupRequestDTO) {
    if (userRepository.userExist(signupRequestDTO.email)) {
      throw new UserAlreadyExistException(USER_ALREADY_EXIST);
    }
    User user = this.userAssembler.createActuary(signupRequestDTO);
    this.userRepository.save(user);
    return authentificationService.addTokenFromUser(user);
  }

  public String getUserEmailFromAuthorizationToken(String authorizationToken) {
    String email = AuthentificationToken.decode(authorizationToken);
    User user = this.userRepository.findByEmail(email);
    if (user == null) {
      throw new UserApiException(email);
    }

    return email;
  }

  public AuthentificationToken signin(SigninRequestDTO signinRequestDTO) {
    String sentEmail = signinRequestDTO.email;
    String hashedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(signinRequestDTO.password);
    User user = this.userRepository.findByEmail(sentEmail);
    if (user == null) {
      throw new UserApiException(sentEmail);
    }
    if (!user.getHashedPassword().equals(hashedPassword)) {
      throw new WrongPasswordException();
    }

    return authentificationService.addTokenFromUser(user);
  }

  public void updateInsurancePolicies() {
    List<Student> students = this.userRepository.findAllStudents();
    for (Student student : students) {
      student.updateInsurancePolicies(this.messageService);
    }
  }
}
