package ca.ulaval.glo4003.projet.base.ws.domain.offer;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import java.math.BigDecimal;
import java.time.Clock;

public interface OfferCalculator {

  QuoteOffer calculateQuoteOffer(Quote quote, Clock clock);

  RenewInsurancePolicyOffer calculatedRenewalInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount);

  UpdateInsurancePolicyOffer calculatedUpdateInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount, BigDecimal newPublicLiabilityAmount);

}
