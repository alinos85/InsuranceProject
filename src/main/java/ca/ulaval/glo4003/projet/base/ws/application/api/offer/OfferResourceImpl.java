package ca.ulaval.glo4003.projet.base.ws.application.api.offer;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.service.offer.AcceptOffer;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import javax.ws.rs.core.Response;

public class OfferResourceImpl implements OfferResource {

  private AcceptOffer acceptOffer;
  private UserService userService;

  public OfferResourceImpl(AcceptOffer acceptOffer, UserService userService) {
    this.acceptOffer = acceptOffer;
    this.userService = userService;
  }

  @Override
  public Response acceptOffer(String authorizationToken, String id) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    InsurancePolicyResponseDTO insurancePolicyResponseDTO = this.acceptOffer.acceptOffer(userEmail, id);
    return Response
        .status(Response.Status.CREATED)
        .entity(insurancePolicyResponseDTO)
        .build();
  }
}
