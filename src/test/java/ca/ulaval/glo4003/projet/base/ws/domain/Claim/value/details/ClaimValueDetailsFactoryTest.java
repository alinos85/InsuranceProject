package ca.ulaval.glo4003.projet.base.ws.domain.Claim.value.details;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.InvalidClaimCategoryException;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetailsFactory;
import org.junit.Before;
import org.junit.Test;

public class ClaimValueDetailsFactoryTest {

  private String INVALID_CLAIM_DETAIL_CATEGORY = "Invalid category";

  private ClaimValueDetailsFactory claimValueDetailsFactory;

  @Before
  public void setUp() {
    claimValueDetailsFactory = new ClaimValueDetailsFactory();
  }

  @Test(expected = InvalidClaimCategoryException.class)
  public void givenAnInvalidClaimDetailCategory_whenBuildingClaimValueDetails_shouldThrowInvalidClaimCategoryException() {
    claimValueDetailsFactory.buildClaimValueDetails(INVALID_CLAIM_DETAIL_CATEGORY);

  }

}
