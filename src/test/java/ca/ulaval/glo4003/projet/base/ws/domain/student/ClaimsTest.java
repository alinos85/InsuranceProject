package ca.ulaval.glo4003.projet.base.ws.domain.student;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimFire;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimThievery;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.spvq.SpvqNumber;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClaimsTest {

  private double RATIO = 2.0;
  private SpvqNumber SPVQ_NUMBER = new SpvqNumber("QUE111111ABC");
  private List<Claim> SPVQ_CLAIMS_LIST = new ArrayList<>();
  private List<Claim> CLAIMS_WITH_HIGHER_AMOUNT_THAN_RATIO = new ArrayList<>();
  private String STATUS = "En analyse";

  private Money HIGHER_INSURANCE_AMOUNT_THAN_RATIO = new Money(new BigDecimal(5000));
  private Money INSURANCE_AMOUNT = new Money(new BigDecimal(2000));
  private Money LOWER_INSURANCE_AMOUNT_THAN_RATIO = new Money(new BigDecimal(100));

  private ClaimThievery SPVQClaim;
  private ClaimFire notSPVQClaim;

  private Claims claims;

  @Before
  public void setUp() {
    SPVQClaim = new ClaimThievery();
    SPVQClaim.addSPVQNumber(SPVQ_NUMBER);
    SPVQClaim.setClaimTotalAmount(HIGHER_INSURANCE_AMOUNT_THAN_RATIO);
    SPVQClaim.setStatus(STATUS);

    notSPVQClaim = new ClaimFire();
    notSPVQClaim.setClaimTotalAmount(LOWER_INSURANCE_AMOUNT_THAN_RATIO);
    notSPVQClaim.setStatus(STATUS);

    claims = new Claims();

    SPVQ_CLAIMS_LIST.add(SPVQClaim);
    CLAIMS_WITH_HIGHER_AMOUNT_THAN_RATIO.add(SPVQClaim);
  }

  @Test
  public void givenAListOfClaim_whenGettingClaimsWithSQPVNumber_thenReturnAllSPVQNumerableClaims() {
    claims.add(SPVQClaim);
    claims.add(notSPVQClaim);

    Assert.assertEquals(SPVQ_CLAIMS_LIST, claims.getClaimsWithSPVQNumber());

  }

  @Test
  public void givenAListOfClaim_whenGettingClaimsWithHigherAmountThanRatio_thenReturnAllClaimsWithHigherAmountThanRatio() {
    claims.add(SPVQClaim);
    claims.add(notSPVQClaim);

    Assert.assertEquals(CLAIMS_WITH_HIGHER_AMOUNT_THAN_RATIO, claims.getClaimsInAnalysisWithHigherAmountThanRatio(RATIO, INSURANCE_AMOUNT));

  }

}

