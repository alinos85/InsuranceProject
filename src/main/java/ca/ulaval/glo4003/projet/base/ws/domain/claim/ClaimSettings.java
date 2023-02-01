package ca.ulaval.glo4003.projet.base.ws.domain.claim;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.ClaimAmountRatioInvalid;

public class ClaimSettings {

  private String OVER_FOUR_RATIO_EXCEPTION = "Claim value ratio cannot be set over 4";
  private String UNDER_ONE_RATIO_EXCEPTION = "Claim value ratio cannot be set under 1";

  // The ratio of the value a user can claim vs the insurance amount he's covered for
  private double claimAmountRatio;

  public ClaimSettings() {
    this.claimAmountRatio = 2;
  }

  public double getClaimAmountRatio() {
    return claimAmountRatio;
  }

  public void setClaimAmountRatio(double claimAmountRatio) {
    this.validClaimAmountRatio(claimAmountRatio);
    this.claimAmountRatio = claimAmountRatio;
  }

  private void validClaimAmountRatio(double claimAmountRatio) {
    if (claimAmountRatio > 4) {
      throw new ClaimAmountRatioInvalid(OVER_FOUR_RATIO_EXCEPTION);
    }
    if (claimAmountRatio < 1) {
      throw new ClaimAmountRatioInvalid(UNDER_ONE_RATIO_EXCEPTION);
    }
  }
}
