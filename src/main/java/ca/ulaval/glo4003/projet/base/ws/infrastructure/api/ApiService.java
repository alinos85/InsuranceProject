package ca.ulaval.glo4003.projet.base.ws.infrastructure.api;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteCalculated;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.QuoteCalculatedAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteCalculatedDTO;
import java.math.BigDecimal;

public class ApiService {

  private ApiDataSource apiDataSource;
  private QuoteCalculatedAssembler quoteCalculatedAssembler;

  public ApiService(ApiDataSource apiDataSource,
      QuoteCalculatedAssembler quoteCalculatedAssembler) {
    this.apiDataSource = apiDataSource;
    this.quoteCalculatedAssembler = quoteCalculatedAssembler;
  }

  public QuoteCalculated calculatedQuoteOffer(Quote quote) {
    QuoteCalculatedDTO response = apiDataSource.callForQuoteOffer(quote);
    return quoteCalculatedAssembler.create(response);
  }

  public QuoteCalculated calculatedInsurancePolicyRenewal(InsurancePolicy insurancePolicy, Money newInsuranceAmount) {
    QuoteCalculatedDTO response = apiDataSource.callForRenewal(insurancePolicy, newInsuranceAmount);
    return quoteCalculatedAssembler.create(response);
  }

  public QuoteCalculated calculatedInsurancePolicyUpdate(InsurancePolicy insurancePolicy, Money newInsuranceAmount, BigDecimal newPublicLiabilityAmount) {
    QuoteCalculatedDTO response = apiDataSource.callForUpdate(insurancePolicy, newInsuranceAmount, newPublicLiabilityAmount);
    return quoteCalculatedAssembler.create(response);
  }

}
