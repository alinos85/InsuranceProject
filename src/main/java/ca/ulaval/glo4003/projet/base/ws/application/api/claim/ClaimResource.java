package ca.ulaval.glo4003.projet.base.ws.application.api.claim;

import ca.ulaval.glo4003.projet.base.ws.application.api.authentication.AuthNeeded;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimSettingsRequestDTO;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/claims")
public interface ClaimResource {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @AuthNeeded
  Response addClaim(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken, @Valid ClaimRequestDTO claimRequestDto);

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  @AuthNeeded
  Response getClaimStatus(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken, @PathParam("id") String id);

  @POST
  @Path("/{claimId}/spvq/{spvqId}/add")
  @AuthNeeded
  Response addSPVQNumber(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken, @PathParam("claimId") String claimId,
      @PathParam("spvqId") String spvqNumber);

  @POST
  @Path("/settings")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @AuthNeeded
  Response updateClaimSettings(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken, @Valid ClaimSettingsRequestDTO claimSettingsRequestDTO);
}
