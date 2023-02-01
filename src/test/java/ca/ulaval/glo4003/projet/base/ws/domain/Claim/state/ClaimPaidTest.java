package ca.ulaval.glo4003.projet.base.ws.domain.Claim.state;

import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimPaid;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimState;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimAlreadyPaidException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ClaimPaidTest {

  private String STATUS_PAID = "Paid";
  @Mock
  private Claim claim;

  private ClaimState claimState;

  @Before
  public void setUp() {
    initMocks(this);
    claimState = new ClaimPaid();
  }

  @Test(expected = ClaimAlreadyPaidException.class)
  public void givenPaidClaimState_whenPaidClaim_shouldThrowClaimAlreadyPaidException() {
    claimState.accept(claim);
  }

  @Test
  public void givenPaidClaimStatus_whenSettingStatus_thenSetStatus() {
    claim.setStatus(STATUS_PAID);
  }
}