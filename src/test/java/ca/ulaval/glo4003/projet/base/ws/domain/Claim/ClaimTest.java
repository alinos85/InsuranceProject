package ca.ulaval.glo4003.projet.base.ws.domain.Claim;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetailsFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClaimTest {

  private String STATUS_CLOSED = "Closed";
  private String FIRST_PRODUCT_NAME = "Meuble et electromenagers";
  private String SECOND_PRODUCT_NAME = "Electronique";
  private Money FIRST_PRODUCT_AMOUNT = new Money(new BigDecimal(200));
  private Money SECOND_PRODUCT_AMOUNT = new Money(new BigDecimal(300));
  private Money TOTAL_PRODUCT_AMOUNT = new Money(new BigDecimal(500));
  private BigDecimal AMOUNT_OVER_CLAIM_TOTAL_AMOUNT = new BigDecimal(1000);
  private BigDecimal AMOUNT_UNDER_CLAIM_TOTAL_AMOUNT = new BigDecimal(400);

  private Claim claim;
  private ClaimValueDetails firstClaimValueDetails;
  private ClaimValueDetails secondClaimValueDetails;
  private List<ClaimValueDetails> claimValueDetailsList;

  @Before
  public void setUp() {
    claim = new Claim() {
    };
    claim.setClaimStatus();

    firstClaimValueDetails = ClaimValueDetailsFactory.buildClaimValueDetails(FIRST_PRODUCT_NAME);
    firstClaimValueDetails.setAmount(FIRST_PRODUCT_AMOUNT);
    secondClaimValueDetails = ClaimValueDetailsFactory.buildClaimValueDetails(SECOND_PRODUCT_NAME);
    secondClaimValueDetails.setAmount(SECOND_PRODUCT_AMOUNT);

    claimValueDetailsList = new ArrayList<ClaimValueDetails>();
    claimValueDetailsList.add(firstClaimValueDetails);
    claimValueDetailsList.add(secondClaimValueDetails);

    claim.setClaimValueDetails(claimValueDetailsList);
  }

  @Test
  public void givenAClaim_whenClosingClaim_thenClaimStatusIsClosed() {
    claim.close();

    Assert.assertEquals(STATUS_CLOSED, claim.getStatus());

  }

  @Test
  public void givenAClaim_whenDefiningClaimTotalAmount_thenClaimTotalAmountIsCalculated() {
    claim.defineClaimTotalAmount();

    Assert.assertEquals(TOTAL_PRODUCT_AMOUNT.amount, claim.getClaimTotalAmount().amount);

  }

  @Test
  public void givenAClaimAmount_whenComparatingWithMaximumValueThatIs_thenClaimTotalAmountIsValidated() {
    claim.defineClaimTotalAmount();

    Boolean validation = claim.validateAmount(AMOUNT_OVER_CLAIM_TOTAL_AMOUNT);

    Assert.assertFalse(validation);

  }

  @Test
  public void givenAClaimAmount_whenComparatingWithMaximumValue_thenClaimTotalAmountIsValidated() {
    claim.defineClaimTotalAmount();

    Boolean validation = claim.validateAmount(AMOUNT_UNDER_CLAIM_TOTAL_AMOUNT);

    Assert.assertTrue(validation);

  }
}
