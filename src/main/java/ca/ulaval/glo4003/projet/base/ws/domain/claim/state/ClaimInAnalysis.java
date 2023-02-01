package ca.ulaval.glo4003.projet.base.ws.domain.claim.state;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;

public class ClaimInAnalysis implements ClaimState {

  private String STATUS_PAID = "Paid";

  @Override
  public void accept(Claim claim) {
    //TODO : Waiting for more details from the client
  }

  @Override
  public void setClaimStatus(Claim claim) {
    claim.setStatus(STATUS_PAID);
  }
}
