package ca.ulaval.glo4003.projet.base.ws.domain.student;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicyId;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.student.exception.NoUpcommingInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.AlreadyActiveInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.AlreadyUpcomingInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.CantRenewInsurancePolicyDueToInactiveException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.CantUpdateInsurancePolicyDueToInactiveException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.CouldNotAddInsurancePolicyException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.EffectivePeriodForActiveInsurancePolicyOverlapCurrentEffectiveDateException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.EffectivePeriodForUpcomingInsurancePolicyOverlapCurrentEffectiveDateException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.exception.PassEffectiveDateForInsurancePolicyException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsurancePolicies {

  private InsurancePolicy activeInsurancePolicy;
  private InsurancePolicy upcomingInsurancePolicy;
  private List<InsurancePolicy> expiredInsurancePolicies;
  private List<InsurancePolicy> cancelledInsurancePolicies;

  public InsurancePolicies() {

    this.expiredInsurancePolicies = new ArrayList<>();
    this.cancelledInsurancePolicies = new ArrayList<>();
  }

  public InsurancePolicy getActiveInsurancePolicy() {
    return activeInsurancePolicy;
  }

  public InsurancePolicy getUpcomingInsurancePolicy() {
    return upcomingInsurancePolicy;
  }

  public List<InsurancePolicy> getExpiredInsurancePolicies() {
    return expiredInsurancePolicies;
  }

  public List<InsurancePolicy> getCancelledInsurancePolicies() {
    return cancelledInsurancePolicies;
  }

  public void update(MessageService messageService) {
    expireActiveInsurancePoliciyIfExpirationDateExceeded();
    setUpcomingInsuranceIfInsuranceIsActive(messageService);
  }

  private void setUpcomingInsuranceIfInsuranceIsActive(MessageService messageService) {
    if (upcomingInsurancePolicy != null && upcomingInsurancePolicy.getEffectiveDate().isEqual(LocalDate.now())) {
      activeInsurancePolicy = upcomingInsurancePolicy;
      upcomingInsurancePolicy = null;
      messageService.sendEffectiveRenewalQuoteMessage(activeInsurancePolicy);
    }
  }

  private void expireActiveInsurancePoliciyIfExpirationDateExceeded() {
    if (this.activeInsurancePolicy.isExpirationDateExceeded()) {
      this.expiredInsurancePolicies.add(this.activeInsurancePolicy);
      this.activeInsurancePolicy = null;
    }
  }

  public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
    addInsurancePolicyValidations(insurancePolicy);
    if (this.activeInsurancePolicy == null && LocalDate.now().isEqual(insurancePolicy.getEffectiveDate())) {
      this.activeInsurancePolicy = insurancePolicy;
    } else if (this.upcomingInsurancePolicy == null && LocalDate.now().isBefore(insurancePolicy.getEffectiveDate())) {
      this.upcomingInsurancePolicy = insurancePolicy;
    } else {
      throw new CouldNotAddInsurancePolicyException();
    }
  }

  public void updateInsurancePolicy(InsurancePolicy insurancePolicy) {
    this.expiredInsurancePolicies.add(this.activeInsurancePolicy);
    this.activeInsurancePolicy = insurancePolicy;
  }

  private void addInsurancePolicyValidations(InsurancePolicy insurancePolicy) {
    if (this.activeInsurancePolicy != null && LocalDate.now().equals(insurancePolicy.getEffectiveDate())) {
      throw new AlreadyActiveInsurancePolicyException();
    }
    if (this.upcomingInsurancePolicy != null && LocalDate.now().isBefore(insurancePolicy.getEffectiveDate())) {
      throw new AlreadyUpcomingInsurancePolicyException();
    }
    if (LocalDate.now().isAfter(insurancePolicy.getEffectiveDate())) {
      throw new PassEffectiveDateForInsurancePolicyException();
    }
    if (this.upcomingInsurancePolicy == null && this.activeInsurancePolicy != null && LocalDate.now().isBefore(insurancePolicy.getEffectiveDate())
        && this.activeInsurancePolicy.isEffectivePeriodOverlapping(insurancePolicy)) {
      throw new EffectivePeriodForUpcomingInsurancePolicyOverlapCurrentEffectiveDateException();
    }
    if (this.upcomingInsurancePolicy != null && this.activeInsurancePolicy == null && LocalDate.now().equals(insurancePolicy.getEffectiveDate())
        && this.upcomingInsurancePolicy.isEffectivePeriodOverlapping(insurancePolicy)) {
      throw new EffectivePeriodForActiveInsurancePolicyOverlapCurrentEffectiveDateException();
    }
    if (this.activeInsurancePolicy != null && LocalDate.now().equals(insurancePolicy.getEffectiveDate())) {
      throw new AlreadyActiveInsurancePolicyException();
    }
    if (this.upcomingInsurancePolicy != null && LocalDate.now().isBefore(insurancePolicy.getEffectiveDate())) {
      throw new AlreadyUpcomingInsurancePolicyException();
    }
  }

  public void cancelUpcomingInsurancePolicy() {
    if (upcomingInsurancePolicy == null) {
      throw new NoUpcommingInsurancePolicyException();
    }
    this.upcomingInsurancePolicy = null;
  }

  public UpdateInsurancePolicyOffer generateUpdateInsurancePolicyOffer(OfferCalculator offerCalculator, Money newInsuranceAmount,
      BigDecimal newPublicLiabilityAmount) {
    if (this.activeInsurancePolicy == null) {
      throw new CantUpdateInsurancePolicyDueToInactiveException();
    }

    return this.activeInsurancePolicy.generateUpdateInsurancePolicyOffer(offerCalculator, newInsuranceAmount, newPublicLiabilityAmount);
  }

  public RenewInsurancePolicyOffer generateRenewInsurancePolicyOffer(OfferCalculator offerCalculator, Money newInsuranceAmount) {
    if (this.activeInsurancePolicy == null) {
      throw new CantRenewInsurancePolicyDueToInactiveException();
    }

    return this.activeInsurancePolicy.generateRenewInsurancePolicyOffer(offerCalculator, newInsuranceAmount);
  }

  public boolean isInsurancePolicyActive(InsurancePolicyId insurancePolicyId) {
    if (activeInsurancePolicy == null) {
      return false;
    }

    return this.activeInsurancePolicy.getId() == insurancePolicyId;
  }
}
