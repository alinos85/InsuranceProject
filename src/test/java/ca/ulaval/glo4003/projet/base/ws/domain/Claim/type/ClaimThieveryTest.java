package ca.ulaval.glo4003.projet.base.ws.domain.Claim.type;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.exception.ClaimNotAnalyzedException;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimThievery;
import org.junit.Before;
import org.junit.Test;

public class ClaimThieveryTest {

  private ClaimThievery claimThievery;

  @Before
  public void setUp() {
    claimThievery = new ClaimThievery() {
    };
  }

  @Test(expected = ClaimNotAnalyzedException.class)
  public void givenNoAnalyzedClaimThievery_whenAcceptingClainThievery_then() {
    claimThievery.accept();
  }
}