package ca.ulaval.glo4003.projet.base.ws.domain.Claim.state;

import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimClosed;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimState;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimClosedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ClaimClosedTest {

  private String STATUS_CLOSED = "Closed";
  @Mock
  private Claim claim;

  private ClaimState claimState;

  @Before
  public void setUp() {
    initMocks(this);
    claimState = new ClaimClosed();
  }

  @Test(expected = ClaimClosedException.class)
  public void givenClosedClaimState_whenClosingClaim_shouldThrowClaimClosedException() {
    claimState.accept(claim);
  }

  @Test
  public void givenClosedClaimStatus_whenSettingStatus_thenSetStatus() {
    claim.setStatus(STATUS_CLOSED);
  }
}