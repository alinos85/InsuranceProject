package ca.ulaval.glo4003.projet.base.ws.domain.claim.state;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimAlreadyAcceptedException;

public class ClaimAccepted implements ClaimState {

  private String CLAIM_ALREADY_ACCEPTED = "Claim is already accepted";
  private String STATUS_ACCEPTED = "Accepted";

  @Override
  public void accept(Claim claim) {
    throw new ClaimAlreadyAcceptedException(CLAIM_ALREADY_ACCEPTED);
  }

  @Override
  public void setClaimStatus(Claim claim) {
    claim.setStatus(STATUS_ACCEPTED);
  }
}

