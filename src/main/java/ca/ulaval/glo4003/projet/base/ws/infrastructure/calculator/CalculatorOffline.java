package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.PriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.building.InsuranceResponsibilityModifier;
import ca.ulaval.glo4003.projet.base.ws.domain.publicliability.PublicLiability;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculatedModifyBuilder;
import java.math.BigDecimal;
import java.time.Clock;

public class CalculatorOffline extends QuoteCalculatedModifyBuilder implements OfferCalculator {

  private static BigDecimal valueAmount = new BigDecimal(2000000);

  public Money calculate(PublicLiability publicLiability, Money price) {

    if (publicLiability.isEqual(valueAmount)) {
      PriceModifierValue responsibility = InsuranceResponsibilityModifier
          .getInstance().modifyPrice(price.amount);
      addPriceModifier(responsibility);
    }

    return applyPriceModifiers(price.amount);
  }

  @Override
  public QuoteOffer calculateQuoteOffer(Quote quote, Clock clock) {
    Money money = calculate(quote.getPublicLiability(), quote.getInsuranceAmount());
    return new QuoteOffer(quote, money, clock);
  }

  @Override
  public RenewInsurancePolicyOffer calculatedRenewalInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount) {
    Money money = calculate(insurancePolicy.getPublicLiability(), newInsuranceAmount);
    return new RenewInsurancePolicyOffer(money, insurancePolicy);
  }

  @Override
  public UpdateInsurancePolicyOffer calculatedUpdateInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount,
      BigDecimal newPublicLiabilityAmount) {
    Money money = calculate(insurancePolicy.getPublicLiability(), insurancePolicy.getInsuranceAmount());
    return new UpdateInsurancePolicyOffer(insurancePolicy, money, newInsuranceAmount, newPublicLiabilityAmount);
  }

}
