package ca.ulaval.glo4003.projet.base.ws.domain.claim;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimClosed;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimReceived;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimState;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.type.ClaimType;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Claim {

  private ClaimId id;
  private String status;
  private ClaimState claimState;
  private List<ClaimValueDetails> claimValuesDetails;
  private ClaimType claimType;
  private LocalDate date;
  private Money claimTotalAmount;

  public Claim() {
    this.id = new ClaimId();
    this.claimState = new ClaimReceived();
    this.date = LocalDate.now();
    this.claimValuesDetails = new ArrayList<>();
    this.claimTotalAmount = new Money(BigDecimal.ZERO);
  }

  public void close() {
    this.claimState = new ClaimClosed();
    this.setClaimStatus();
  }

  public void accept() {
    claimState.accept(this);
    this.setClaimStatus();
  }

  public void setClaimStatus() {
    claimState.setClaimStatus(this);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<ClaimValueDetails> getClaimValueDetails() {
    return claimValuesDetails;
  }

  public void setClaimValueDetails(List<ClaimValueDetails> claimValuesDetails) {
    this.claimValuesDetails = claimValuesDetails;
  }

  public ClaimId getId() {
    return id;
  }

  public ClaimType getClaimType() {
    return claimType;
  }

  public void setClaimType(ClaimType claimType) {
    this.claimType = claimType;
  }

  public void defineClaimTotalAmount() {
    Money totalAmount = new Money(BigDecimal.ZERO);
    for (ClaimValueDetails claimValueDetail : this.claimValuesDetails) {
      totalAmount.add(claimValueDetail.getAmount());
    }
    this.claimTotalAmount = totalAmount;
  }

  public Money getClaimTotalAmount() {
    return claimTotalAmount;
  }

  public void setClaimTotalAmount(Money amount) {
    this.claimTotalAmount = amount;
  }

  public boolean validateAmount(BigDecimal maximumValue) {
    return maximumValue.compareTo(this.claimTotalAmount.amount) == -1;
  }
}
