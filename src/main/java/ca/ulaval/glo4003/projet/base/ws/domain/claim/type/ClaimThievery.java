package ca.ulaval.glo4003.projet.base.ws.domain.claim.type;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.SPVQNumerableClaim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.InvalidClaimStatusException;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimClosed;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimReceived;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.state.ClaimState;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details.ClaimValueDetails;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.spvq.SpvqNumber;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ClaimThievery extends Claim implements SPVQNumerableClaim {

  private String INVALID_CLAIM_STATUS = "Invalid claim status for this action";
  private int EXPIRATION_TIME_IN_DAY = 5;

  private String idContract;
  private String id;
  private String status;
  private ClaimState claimState;
  private List<ClaimValueDetails> claimValuesDetails;
  private ClaimType claimType;
  private LocalDate date;
  private SpvqNumber spvqNumber;

  public ClaimThievery() {
    this.id = UUID.randomUUID().toString();
    this.claimState = new ClaimReceived();
    this.date = LocalDate.now();
    this.spvqNumber = null;
  }

  public SpvqNumber getSpvqNumber() {
    return spvqNumber;
  }

  @Override
  public void addSPVQNumber(SpvqNumber spvqNumber) {
    if (!(this.claimState instanceof ClaimReceived)) {
      throw new InvalidClaimStatusException(INVALID_CLAIM_STATUS);
    }
    this.spvqNumber = spvqNumber;
  }

  @Override
  public void expireIfSPVQNumberNotSuppliedBeforeDeadline(MessageService messageService, Messageable user) {
    if (this.isNotClosedAndSpvqNumberNotSuppliedBeforeDeadline()) {
      this.close();
      messageService.sendClaimExpireMessage(this, user);
    }
  }

  private boolean isNotClosedAndSpvqNumberNotSuppliedBeforeDeadline() {
    return !isClosed() && !LocalDate.now().isBefore(this.date.plusDays(EXPIRATION_TIME_IN_DAY)) && spvqNumber == null;
  }

  private boolean isClosed() {
    return this.claimState instanceof ClaimClosed;
  }
}
