package ca.ulaval.glo4003.projet.base.ws.domain.student;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimId;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.SPVQNumerableClaim;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Claims {

  private HashMap<String, Claim> expiredClaims;
  private HashMap<String, Claim> activeClaims;

  public Claims() {
    this.expiredClaims = new HashMap<>();
    this.activeClaims = new HashMap<>();
  }

  public void add(Claim claim) {
    this.activeClaims.put(claim.getId().value, claim);
  }

  public Claim get(ClaimId claimId) {
    return this.activeClaims.get(claimId.value);
  }

  public List<Claim> getClaimsWithSPVQNumber() {
    List<Claim> spvqNumberClaims = new ArrayList<>();

    for (HashMap.Entry<String, Claim> claim : activeClaims.entrySet()) {
      if (claim.getValue() instanceof SPVQNumerableClaim) {
        spvqNumberClaims.add(claim.getValue());
      }
    }

    return spvqNumberClaims;
  }

  public List<Claim> getClaimsInAnalysisWithHigherAmountThanRatio(double claimAmountRatio, Money insuranceAmount) {
    List<Claim> claimsInAnalysisWithHigherAmountThanRatio = new ArrayList<>();
    for (Claim claim : this.activeClaims.values()) {
      if (claim.getStatus().equals("En analyse") && claim.validateAmount(insuranceAmount.amount.multiply(new BigDecimal(claimAmountRatio)))) {
        claimsInAnalysisWithHigherAmountThanRatio.add(claim);
      }
    }
    return claimsInAnalysisWithHigherAmountThanRatio;
  }
}

