package ca.ulaval.glo4003.projet.base.ws.domain.claim.state;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;

public interface ClaimState {

  void accept(Claim claim);

  void setClaimStatus(Claim claim);
}
