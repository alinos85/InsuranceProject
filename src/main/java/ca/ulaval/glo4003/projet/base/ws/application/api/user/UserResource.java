package ca.ulaval.glo4003.projet.base.ws.application.api.user;

import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SigninRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public interface UserResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/student/signup")
  Response signupStudent(@Valid SignupRequestDTO signupRequestDTO);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/actuary/signup")
  Response signupActuary(@Valid SignupRequestDTO signupRequestDTO);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/signin")
  Response signin(@Valid SigninRequestDTO signinRequestDTO);
}
