package ca.ulaval.glo4003.projet.base.ws.application.api.claim;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimIdResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimSettingsRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimStatusResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimsResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.service.actuary.UpdateClaimAmountRatio;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.claim.AddSPVQNumberToClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GenerateClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GetClaimStatus;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

public class ClaimResourceImpl implements ClaimResource {

  private static final Logger LOGGER = Logger.getLogger(AuthentificationService.class.getName());
  private UserService userService;
  private GenerateClaim generateClaim;
  private GetClaimStatus getClaimStatus;
  private AddSPVQNumberToClaim addSPVQNumberToClaim;
  private UpdateClaimAmountRatio updateClaimAmountRatio;

  public ClaimResourceImpl(UserService userService, GenerateClaim generateClaim, GetClaimStatus getClaimStatus, AddSPVQNumberToClaim addSPVQNumberToClaim,
      UpdateClaimAmountRatio updateClaimAmountRatio) {
    this.userService = userService;
    this.generateClaim = generateClaim;
    this.getClaimStatus = getClaimStatus;
    this.addSPVQNumberToClaim = addSPVQNumberToClaim;
    this.updateClaimAmountRatio = updateClaimAmountRatio;
  }

  @Override
  public Response addClaim(String authorizationToken, ClaimRequestDTO claimRequestDto) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    String idClaim = generateClaim.addClaim(userEmail, claimRequestDto);
    ClaimIdResponseDTO claimIdResponseDTO = new ClaimIdResponseDTO();
    claimIdResponseDTO.idClaim = idClaim;
    LOGGER.info("Claim added: Claim id " + idClaim);
    return Response
        .status(Response.Status.CREATED)
        .entity(claimIdResponseDTO)
        .build();
  }

  @Override
  public Response getClaimStatus(String authorizationToken, String id) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    ClaimStatusResponseDTO claimStatusDTO = new ClaimStatusResponseDTO();
    claimStatusDTO.claimStatus = this.getClaimStatus.getClaimStatus(userEmail, id);
    return Response.ok().entity(claimStatusDTO).build();
  }

  @Override
  public Response addSPVQNumber(String authorizationToken, String claimId, String spvqNumber) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    this.addSPVQNumberToClaim.addSPVQNumber(userEmail, claimId, spvqNumber);
    LOGGER.info("SPVQ number added. SPVQ number: " + spvqNumber);
    return Response.accepted().build();
  }

  @Override
  public Response updateClaimSettings(String authorizationToken, @Valid ClaimSettingsRequestDTO claimSettingsRequestDTO) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    ClaimsResponseDTO claimsResponseDTO = updateClaimAmountRatio.update(userEmail, claimSettingsRequestDTO.claimsAmountRatio);
    LOGGER.info("Claim amount ratio changed");
    return Response.accepted().entity(claimsResponseDTO).build();
  }
}
