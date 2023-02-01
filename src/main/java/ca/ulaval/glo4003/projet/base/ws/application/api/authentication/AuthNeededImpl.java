package ca.ulaval.glo4003.projet.base.ws.application.api.authentication;

import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@AuthNeeded
@Priority(Priorities.AUTHENTICATION)
public class AuthNeededImpl implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) {

    try {
      // Get the HTTP Authorization header from the request
      String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
      // Validate the token
      String email = AuthentificationToken.decode(token);
    } catch (Exception e) {
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }
}