package ca.ulaval.glo4003.projet.base.ws.domain.student;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimId;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.SPVQNumerableClaim;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.ClaimNotFoundExeption;
import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.Offer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.exception.OfferNotFoundExeption;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.OfferId;
import ca.ulaval.glo4003.projet.base.ws.domain.spvq.SpvqNumber;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.ClaimAmountAboveTheLimitException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.NoActiveInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.SPVQNumberDoesNotApplyToThisClaimTypeException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements Messageable, User {

  private String CLAIM_TYPE_IS_INVALID = "Adding SPVQ Number doesn't apply to this type of claim";
  private String CLAIM_AMOUNT_ABOVE_THE_LIMIT = "";

  private String hashedPassword;
  private EmailAddress emailAddress;
  private Claims claims;
  private InsurancePolicies insurancePolicies;
  private Map<String, Offer> offers;

  public Student(EmailAddress emailAddress, String hashedPassword) {
    this.emailAddress = emailAddress;
    this.hashedPassword = hashedPassword;
    this.claims = new Claims();
    insurancePolicies = new InsurancePolicies();
    offers = new HashMap<>();
  }

  public String getId() {
    return this.emailAddress.value;
  }

  public String getHashedPassword() {
    return this.hashedPassword;
  }

  public EmailAddress getEmailAddress() {
    return this.emailAddress;
  }

  public void updateInsurancePolicies(MessageService messageService) {
    this.insurancePolicies.update(messageService);
  }

  public InsurancePolicy acceptQuoteOffer(QuoteOffer quoteOffer) {
    InsurancePolicy insurancePolicy = quoteOffer.accept(this);
    return insurancePolicy;
  }

  public InsurancePolicy acceptOffer(OfferId offerId) {
    Offer offer = this.getOffer(offerId);

    InsurancePolicy insurancePolicy = offer.accept(this);

    return insurancePolicy;
  }

  public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
    this.insurancePolicies.addInsurancePolicy(insurancePolicy);
  }

  public boolean isInsurancePolicyActive(InsurancePolicyId insurancePolicyId) {
    return this.insurancePolicies.isInsurancePolicyActive(insurancePolicyId);
  }

  public Offer getOffer(OfferId offerId) {
    Offer offer = this.offers.get(offerId.value);
    if (offer == null) {
      throw new OfferNotFoundExeption(offerId.value);
    }

    return offer;
  }

  public void addClaim(Claim claim, double claimRatio) {
    this.validateInsurancePolicyIsActive();
    this.validateClaimAmountAndInsuranceAmountsRatio(claim, claimRatio);
    this.claims.add(claim);
  }

  private void validateInsurancePolicyIsActive() {
    if (this.insurancePolicies.getActiveInsurancePolicy() == null) {
      throw new NoActiveInsurancePolicyException();
    }
  }

  private void validateClaimAmountAndInsuranceAmountsRatio(Claim claim, double claimRatio) {
    BigDecimal activeInsurancePolicyAmount = this.insurancePolicies.getActiveInsurancePolicy().getInsuranceAmount().amount;
    BigDecimal claimAmount = claim.getClaimTotalAmount().amount;
    if ((activeInsurancePolicyAmount.multiply(new BigDecimal(claimRatio))).compareTo(claimAmount) == -1) {
      throw new ClaimAmountAboveTheLimitException(Double.toString(claimRatio));
    }
  }

  public RenewInsurancePolicyOffer requestRenewInsurancePolicy(OfferCalculator offerCalculator, Money newInsuranceAmount) {

    RenewInsurancePolicyOffer renewInsurancePolicyOffer = insurancePolicies.generateRenewInsurancePolicyOffer(offerCalculator, newInsuranceAmount);

    this.addOffer(renewInsurancePolicyOffer);

    return renewInsurancePolicyOffer;
  }

  public void addOffer(Offer offer) {
    this.offers.put(offer.getOfferId().value, offer);
  }

  public void closeClaimsWithoutSpvqNumberSuppliedBeforeDeadline(MessageService messageService) {
    for (Claim claim : this.claims.getClaimsWithSPVQNumber()) {
      ((SPVQNumerableClaim) claim).expireIfSPVQNumberNotSuppliedBeforeDeadline(messageService, this);
    }
  }

  public void addSPVQNumberToClaim(ClaimId claimId, SpvqNumber spvqNumber) {
    Claim claim = this.getClaim(claimId);

    if (claim instanceof SPVQNumerableClaim) {
      ((SPVQNumerableClaim) claim).addSPVQNumber(spvqNumber);
    } else {
      throw new SPVQNumberDoesNotApplyToThisClaimTypeException(CLAIM_TYPE_IS_INVALID);
    }
  }

  public Claim getClaim(ClaimId claimId) {
    Claim claim = this.claims.get(claimId);
    if (claim == null) {
      throw new ClaimNotFoundExeption(claimId.value);
    }

    return claim;
  }

  public void updateInsurancePolicy(InsurancePolicy insurancePolicy) {
    this.insurancePolicies.updateInsurancePolicy(insurancePolicy);
  }

  public UpdateInsurancePolicyOffer updateInsurancePolicy(OfferCalculator offerCalculator, Money newInsuranceAmount, BigDecimal newPublicLiabilityAmount) {
    UpdateInsurancePolicyOffer updateInsurancePolicyOffer = this.insurancePolicies
        .generateUpdateInsurancePolicyOffer(offerCalculator, newInsuranceAmount, newPublicLiabilityAmount);

    this.addOffer(updateInsurancePolicyOffer);

    return updateInsurancePolicyOffer;
  }

  public void cancelUpcomingInsurancePolicy() {
    this.insurancePolicies.cancelUpcomingInsurancePolicy();
  }

  public List<Claim> getClaimsInAnalysisWithHigherAmountThanRatio(double claimAmountRatio) {
    if (this.insurancePolicies.getActiveInsurancePolicy() == null) {
      return new ArrayList<>();
    }

    return this.claims.getClaimsInAnalysisWithHigherAmountThanRatio(claimAmountRatio,
        this.insurancePolicies.getActiveInsurancePolicy().getInsuranceAmount());
  }
}
