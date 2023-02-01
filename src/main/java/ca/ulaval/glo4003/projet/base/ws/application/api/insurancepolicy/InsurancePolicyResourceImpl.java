package ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.RenewInsurancePolicyRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.UpdatePolicyDTO;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.CancelUpcommingInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.RenewInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.UpdateInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.net.URI;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class InsurancePolicyResourceImpl implements InsurancePolicyResource {

  private static final Logger LOGGER = Logger.getLogger(AuthentificationService.class.getName());
  private UserService userService;
  private RenewInsurancePolicyService renewInsurancePolicyService;
  private UpdateInsurancePolicyService updateInsurancePolicyService;
  private CancelUpcommingInsurancePolicy cancelUpcommingInsurancePolicy;

  public InsurancePolicyResourceImpl(UserService userService,
      RenewInsurancePolicyService renewInsurancePolicyService, UpdateInsurancePolicyService updateInsurancePolicyService,
      CancelUpcommingInsurancePolicy cancelUpcommingInsurancePolicy) {
    this.userService = userService;
    this.renewInsurancePolicyService = renewInsurancePolicyService;
    this.updateInsurancePolicyService = updateInsurancePolicyService;
    this.cancelUpcommingInsurancePolicy = cancelUpcommingInsurancePolicy;
  }

  @Override
  public Response updateInsurancePolicy(UriInfo uriInfo, String authorizationToken, UpdatePolicyDTO updatePolicyDTO) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    LOGGER.info("Update Insurance Policy");
    OfferResponseDTO offerResponseDTO = updateInsurancePolicyService
        .updateInsurancePolicyRequest(userEmail, updatePolicyDTO.insuranceAmount, updatePolicyDTO.publicLiabilityAmount);
    return Response
        .status(Response.Status.CREATED)
        .location(URI.create(uriInfo.getBaseUri() + "quote/offer/" + offerResponseDTO.offerId + "/accept"))
        .entity(offerResponseDTO)
        .build();
  }

  @Override
  public Response renewInsurancePolicy(UriInfo uriInfo, String authorizationToken, RenewInsurancePolicyRequestDTO renewInsurancePolicyRequestDTO) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    LOGGER.info("Renew Insurance Policy");
    OfferResponseDTO offerResponseDTO = renewInsurancePolicyService.renewInsurancePolicyRequest(userEmail, renewInsurancePolicyRequestDTO.insuranceAmount);
    return Response
        .status(Response.Status.CREATED)
        .location(URI.create(uriInfo.getBaseUri() + "quote/offer/" + offerResponseDTO.offerId + "/accept"))
        .entity(offerResponseDTO)
        .build();
  }

  @Override
  public Response cancelRenewInsurancePolicy(String authorizationToken) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    cancelUpcommingInsurancePolicy.cancelUpcommingInsurancePolicy(userEmail);

    return Response.status(Response.Status.OK).build();
  }
}