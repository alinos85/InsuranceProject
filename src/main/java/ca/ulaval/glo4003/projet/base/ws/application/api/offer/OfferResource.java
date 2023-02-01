package ca.ulaval.glo4003.projet.base.ws.application.api.offer;

import ca.ulaval.glo4003.projet.base.ws.application.api.authentication.AuthNeeded;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/offer")
public interface OfferResource {

  @POST
  @Path("/{id}/accept")
  @AuthNeeded
  Response acceptOffer(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken, @PathParam("id") String id);
}
