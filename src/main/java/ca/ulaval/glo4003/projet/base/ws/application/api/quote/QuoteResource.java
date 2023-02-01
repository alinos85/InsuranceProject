package ca.ulaval.glo4003.projet.base.ws.application.api.quote;

import ca.ulaval.glo4003.projet.base.ws.application.api.authentication.AuthNeeded;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/quote")
public interface QuoteResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response createQuote(@Context UriInfo uriInfo, @Valid QuoteRequestDTO quoteRequestDTO);

  @POST
  @Path("/offer/{id}/accept")
  @AuthNeeded
  Response acceptQuoteOffer(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken, @PathParam("id") String id);

  @GET
  @Path("/{id}")
  Response getQuoteInformations(@PathParam("id") String id);
}
