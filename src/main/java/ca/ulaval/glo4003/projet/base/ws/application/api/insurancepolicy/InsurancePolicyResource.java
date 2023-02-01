package ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.application.api.authentication.AuthNeeded;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.RenewInsurancePolicyRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.UpdatePolicyDTO;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/insurancepolicy")
public interface InsurancePolicyResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/update")
  @AuthNeeded
  Response updateInsurancePolicy(@Context UriInfo uriInfo, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken,
      @Valid UpdatePolicyDTO updatePolicyDTO);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/renew")
  @AuthNeeded
  Response renewInsurancePolicy(@Context UriInfo uriInfo, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken,
      @Valid RenewInsurancePolicyRequestDTO renewInsurancePolicyRequestDTO);

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/upcoming")
  @AuthNeeded
  Response cancelRenewInsurancePolicy(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationToken);
}