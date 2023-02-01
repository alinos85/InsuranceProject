package ca.ulaval.glo4003.projet.base.ws.application.api.user;

import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SigninRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public class UserResourceImpl implements UserResource {

  private static final Logger LOGGER = Logger.getLogger(AuthentificationService.class.getName());
  private UserService userService;

  public UserResourceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Response signupStudent(@Valid SignupRequestDTO signupRequestDTO) {
    AuthentificationToken authentificationToken = this.userService.createStudent(signupRequestDTO);

    LOGGER.info("Student sign up.");
    return Response
        .status(Response.Status.CREATED)
        .header(HttpHeaders.AUTHORIZATION, authentificationToken.getTokenString())
        .build();
  }

  @Override
  public Response signupActuary(@Valid SignupRequestDTO signupRequestDTO) {
    AuthentificationToken authentificationToken = this.userService.createActuary(signupRequestDTO);

    LOGGER.info("Actuary sign up.");
    return Response
        .status(Response.Status.CREATED)
        .header(HttpHeaders.AUTHORIZATION, authentificationToken.getTokenString())
        .build();
  }

  @Override
  public Response signin(SigninRequestDTO signinRequestDTO) {
    AuthentificationToken authentificationToken = this.userService.signin(signinRequestDTO);

    LOGGER.info("User sign in.");
    return Response
        .status(Response.Status.OK)
        .header(HttpHeaders.AUTHORIZATION, authentificationToken.getTokenString())
        .build();
  }
}
