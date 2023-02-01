package ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.RenewInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.UpdateInsurancePolicyOffer;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.UrlConnectionValidation;
import java.math.BigDecimal;
import java.time.Clock;

public class CalculatorProxy implements OfferCalculator {

  private ApiService apiService;
  private UrlConnectionValidation urlConnectionValidation;

  public CalculatorProxy(ApiService apiService, UrlConnectionValidation urlConnectionValidation) {
    this.apiService = apiService;
    this.urlConnectionValidation = urlConnectionValidation;
  }

  public OfferCalculator getQuoteOfferCalculator() {
    if (urlConnectionValidation.pingHost("https://ulaval-tenants-quote.herokuapp.com")) {
      return new CalculatorApi(apiService);
    } else {
      return new CalculatorOffline();
    }

  }

  @Override
  public QuoteOffer calculateQuoteOffer(Quote quote, Clock clock) {
    return this.getQuoteOfferCalculator().calculateQuoteOffer(quote, clock);
  }

  @Override
  public RenewInsurancePolicyOffer calculatedRenewalInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount) {
    return this.getQuoteOfferCalculator().calculatedRenewalInsurancePolicy(insurancePolicy, newInsuranceAmount);
  }

  @Override
  public UpdateInsurancePolicyOffer calculatedUpdateInsurancePolicy(InsurancePolicy insurancePolicy, Money newInsuranceAmount,
      BigDecimal newPublicLiabilityAmount) {
    return this.getQuoteOfferCalculator().calculatedUpdateInsurancePolicy(insurancePolicy, newInsuranceAmount, newPublicLiabilityAmount);
  }

}
