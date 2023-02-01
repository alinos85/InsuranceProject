package ca.ulaval.glo4003.projet.base.ws.domain.Claim.factory;

import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.factory.ClaimFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.factory.ClaimTypeApiException;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimFire;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimThievery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ClaimFactoryTest {

  private String FIRE_TYPE_CLAIM = "FIRE";
  private String THIEVERY_TYPE_CLAIM = "THIEVERY";

  @Mock
  private ClaimFactory claimFactory;

  @Before
  public void setUp() {
    initMocks(this);
    claimFactory = new ClaimFactory();
  }

  @Test
  public void givenFireTypeClaim_whenBuildClaim_thenNotReturnException() {
    claimFactory.buildClaim(FIRE_TYPE_CLAIM);

    Assert.assertNotNull(claimFactory);

  }

  @Test
  public void givenThieveryTypeClaim_whenBuildClaim_thenNotReturnException() {
    claimFactory.buildClaim(THIEVERY_TYPE_CLAIM);

    Assert.assertNotNull(claimFactory);

  }

  @Test(expected = ClaimTypeApiException.class)
  public void givenNullTypeClaim_whenBuildClaim_shouldThrowClaimAlreadyAcceptedException() {
    claimFactory.buildClaim(null);

    Assert.assertNotNull(claimFactory);

  }

  @Test
  public void givenFireTypeClaim_whenBuildClaim_thenReturnClaimFire() {
    Claim claim = claimFactory.buildClaim(FIRE_TYPE_CLAIM);

    Assert.assertTrue(claim instanceof ClaimFire);

  }

  @Test
  public void givenThieveryTypeClaim_whenBuildClaim_thenReturnClaimTypeThievery() {
    Claim claim = claimFactory.buildClaim(THIEVERY_TYPE_CLAIM);

    Assert.assertTrue(claim instanceof ClaimThievery);

  }
}