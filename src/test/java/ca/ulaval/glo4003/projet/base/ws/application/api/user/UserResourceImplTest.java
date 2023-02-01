package ca.ulaval.glo4003.projet.base.ws.application.api.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SigninRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class UserResourceImplTest {

  private AuthentificationToken authentificationToken = new AuthentificationToken("token");

  private String EMAIL = "A@A.A";
  private String PASSWORD = "A";
  private SignupRequestDTO signupRequestDTO;
  private SigninRequestDTO signinRequestDTO;

  @Mock
  private UserService userService;

  private UserResource userResource;

  @Before
  public void setUp() {
    initMocks(this);

    userResource = new UserResourceImpl(userService);

    setUpSignInRequestDTO();
    setUpSignUpRequestDTO();
    setUpUserServiceMock();
  }

  private void setUpUserServiceMock() {
    willReturn(authentificationToken).given(userService).createStudent(signupRequestDTO);
    willReturn(authentificationToken).given(userService).createActuary(signupRequestDTO);
    willReturn(authentificationToken).given(userService).signin(signinRequestDTO);
  }

  private void setUpSignInRequestDTO() {
    signinRequestDTO = new SigninRequestDTO();
    signinRequestDTO.password = PASSWORD;
    signinRequestDTO.email = EMAIL;
  }

  private void setUpSignUpRequestDTO() {
    signupRequestDTO = new SignupRequestDTO();
    signupRequestDTO.email = EMAIL;
    signupRequestDTO.password = PASSWORD;
  }

  @Test
  public void givenSignUpRequestDTO_whenSignUpStudent_thenCreateStudent() {
    Response response = userResource.signupStudent(signupRequestDTO);

    verify(userService).createStudent(signupRequestDTO);
    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void signupActuary() {
    Response response = userResource.signupActuary(signupRequestDTO);

    verify(userService).createActuary(signupRequestDTO);
    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void signin() {
    Response response = userResource.signin(signinRequestDTO);

    verify(userService).signin(signinRequestDTO);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}