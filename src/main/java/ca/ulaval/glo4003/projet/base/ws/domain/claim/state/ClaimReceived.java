package ca.ulaval.glo4003.projet.base.ws.domain.claim.state;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimNotAnalyzedException;

public class ClaimReceived implements ClaimState {

  private String CLAIM_NOT_ANALYSED = "Claim has not been analysed yet";
  private String STATUS_RECEIVED = "Received";

  @Override
  public void accept(Claim claim) {
    throw new ClaimNotAnalyzedException(CLAIM_NOT_ANALYSED);
  }

  @Override
  public void setClaimStatus(Claim claim) {
    claim.setStatus(STATUS_RECEIVED);
  }
}
