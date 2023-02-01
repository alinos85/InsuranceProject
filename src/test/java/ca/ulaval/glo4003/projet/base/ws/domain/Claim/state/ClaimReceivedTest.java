package ca.ulaval.glo4003.projet.base.ws.domain.Claim.state;

import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimReceived;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimState;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimNotAnalyzedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ClaimReceivedTest {

  private String STATUS_RECEIVED = "Received";
  @Mock
  private Claim claim;

  private ClaimState claimState;

  @Before
  public void setUp() {
    initMocks(this);
    claimState = new ClaimReceived();
  }

  @Test(expected = ClaimNotAnalyzedException.class)
  public void givenReceivedClaimState_whenReceivedClaim_shouldThrowClaimAlreadyPaidException() {
    claimState.accept(claim);
  }

  @Test
  public void givenReceivedClaimStatus_whenSettingStatus_thenSetStatus() {
    claim.setStatus(STATUS_RECEIVED);
  }
}