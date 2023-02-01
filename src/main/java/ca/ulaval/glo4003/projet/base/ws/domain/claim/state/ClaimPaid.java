package ca.ulaval.glo4003.projet.base.ws.domain.claim.state;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimAlreadyPaidException;

public class ClaimPaid implements ClaimState {

  private String CLAIM_ALREADY_PAID = "Claim is already paid";
  private String STATUS_PAID = "Paid";

  @Override
  public void accept(Claim claim) {
    throw new ClaimAlreadyPaidException(CLAIM_ALREADY_PAID);
  }

  @Override
  public void setClaimStatus(Claim claim) {
    claim.setStatus(STATUS_PAID);
  }
}
