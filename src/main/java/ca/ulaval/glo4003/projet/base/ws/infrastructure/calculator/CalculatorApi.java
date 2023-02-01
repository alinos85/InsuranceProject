package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculated;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculatedModifyBuilder;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import java.math.BigDecimal;
import java.time.Clock;

public class CalculatorApi extends QuoteCalculatedModifyBuilder implements OfferCalculator {

  private ApiService apiService;

  public CalculatorApi(ApiService apiService) {
    this.apiService = apiService;
  }

  @Override
  public QuoteOffer calculateQuoteOffer(Quote quote, Clock clock) {
    QuoteCalculated quoteCalculated = apiService.calculatedQuoteOffer(quote);
    BigDecimal price = quoteCalculated.getPrice();
    Money money = applyPriceModifiers(price);
    return new QuoteOffer(quote, money, clock);
  }

  @Override
  public RenewInsurancePolicyOffer calculatedRenewalInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount) {
    QuoteCalculated quoteCalculated = apiService.calculatedInsurancePolicyRenewal(insurancePolicy, newInsuranceAmount);
    BigDecimal price = quoteCalculated.getPrice();
    Money money = this.applyPriceModifiers(price);
    return new RenewInsurancePolicyOffer(money, insurancePolicy);
  }

  @Override
  public UpdateInsurancePolicyOffer calculatedUpdateInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount,
      BigDecimal newPublicLiabilityAmount) {
    QuoteCalculated quoteCalculated = apiService.calculatedInsurancePolicyUpdate(insurancePolicy, newInsuranceAmount, newPublicLiabilityAmount);
    BigDecimal price = quoteCalculated.getPrice();
    Money money = this.applyPriceModifiers(price);
    return new UpdateInsurancePolicyOffer(insurancePolicy, money, newInsuranceAmount, newPublicLiabilityAmount);
  }

}
