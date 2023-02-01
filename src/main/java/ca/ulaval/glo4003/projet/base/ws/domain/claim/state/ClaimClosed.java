package ca.ulaval.glo4003.projet.base.ws.domain.claim.state;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimClosedException;

public class ClaimClosed implements ClaimState {

  private String CLAIM_IS_CLOSED = "This Claim is closed";
  private String STATUS_CLOSED = "Closed";

  @Override
  public void accept(Claim claim) {
    throw new ClaimClosedException(CLAIM_IS_CLOSED);
  }

  @Override
  public void setClaimStatus(Claim claim) {
    claim.setStatus(STATUS_CLOSED);
  }
}

