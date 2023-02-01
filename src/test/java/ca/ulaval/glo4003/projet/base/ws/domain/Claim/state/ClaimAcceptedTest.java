package ca.ulaval.glo4003.projet.base.ws.domain.Claim.state;

import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimAccepted;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimState;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimAlreadyAcceptedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ClaimAcceptedTest {

  private String STATUS_ACCEPTED = "Accepted";
  @Mock
  private Claim claim;

  private ClaimState claimState;

  @Before
  public void setUp() {
    initMocks(this);
    claimState = new ClaimAccepted();
  }

  @Test(expected = ClaimAlreadyAcceptedException.class)
  public void givenAcceptedClaimState_whenAcceptingClaim_shouldThrowClaimAlreadyAcceptedException() {
    claimState.accept(claim);
  }

  @Test
  public void givenAcceptedClaimStatus_whenSettingStatus_thenSetStatus() {
    claimState.setClaimStatus(claim);
  }
}