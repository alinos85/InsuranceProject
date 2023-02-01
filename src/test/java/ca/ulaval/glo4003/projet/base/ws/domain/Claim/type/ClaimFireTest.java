package ca.ulaval.glo4003.projet.base.ws.domain.Claim.type;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimNotAnalyzedException;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimFire;
import org.junit.Before;
import org.junit.Test;

public class ClaimFireTest {

  private ClaimFire claimFire;

  @Before
  public void setUp() {
    claimFire = new ClaimFire() {
    };
  }

  @Test(expected = ClaimNotAnalyzedException.class)
  public void givenNoAnalyzedClaimFire_whenAcceptingClainFire_then() {
    claimFire.accept();
  }
}