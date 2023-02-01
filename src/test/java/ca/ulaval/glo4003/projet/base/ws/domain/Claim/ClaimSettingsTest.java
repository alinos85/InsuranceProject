package ca.ulaval.glo4003.projet.base.ws.domain.Claim;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimSettings;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.ClaimAmountRatioInvalid;
import org.junit.Before;
import org.junit.Test;

public class ClaimSettingsTest {

  private double OVER_FOUR_RATIO = 5.0;
  private double UNDER_ONE_RATIO = 0.5;

  private ClaimSettings claimSettings;

  @Before
  public void setUp() {
    claimSettings = new ClaimSettings();
  }

  @Test(expected = ClaimAmountRatioInvalid.class)
  public void givenAClaimAmountSetting_whenDefiningClaimAmountRatioOverFour_shouldThrowClaimAmountRatioInvalidException() {
    claimSettings.setClaimAmountRatio(OVER_FOUR_RATIO);

  }

  @Test(expected = ClaimAmountRatioInvalid.class)
  public void givenAClaimAmountSetting_whenDefiningClaimAmountRatioUnderTwo_shouldThrowClaimAmountRatioInvalidException() {
    claimSettings.setClaimAmountRatio(UNDER_ONE_RATIO);

  }

}
