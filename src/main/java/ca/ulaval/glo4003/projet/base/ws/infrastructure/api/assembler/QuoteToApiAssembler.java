package ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteApiDto;
import java.math.BigDecimal;

public class QuoteToApiAssembler {

  private AddressToApiAssembler addressToApiAssembler;

  public QuoteToApiAssembler(AddressToApiAssembler addressToApiAssembler) {
    this.addressToApiAssembler = addressToApiAssembler;
  }

  public QuoteApiDto quoteToDao(Quote quote) {
    QuoteApiDto quoteApiDto = new QuoteApiDto();
    quoteApiDto.birthdate = quote.getIdentity().birthday;
    quoteApiDto.name = quote.getIdentity().name;
    quoteApiDto.address = addressToApiAssembler.toDao(quote.getAddress());
    quoteApiDto.sex = toGender(quote.getIdentity().gender);
    quoteApiDto.numberOfTenants = new BigDecimal(quote.getApartmentDetails().getNbOfHousing());
    quoteApiDto.withBusiness = quote.getApartmentDetails().getHasCommerce();
    quoteApiDto.withCentralAlarm = quote.getApartmentDetails().getHasAlarm();
    quoteApiDto.withSprinkler = quote.getApartmentDetails().getHasJet();
    quoteApiDto.insuredAmount = quote.getInsuranceAmount().amount.intValue();
    quoteApiDto.effectiveDate = quote.getEffectiveDate().toString();
    return quoteApiDto;
  }

  public QuoteApiDto quoteToDao(InsurancePolicy insurancePolicy) {
    QuoteApiDto quoteApiDto = new QuoteApiDto();
    quoteApiDto.birthdate = insurancePolicy.getIdentity().birthday;
    quoteApiDto.name = insurancePolicy.getIdentity().name;
    quoteApiDto.address = addressToApiAssembler.toDao(insurancePolicy.getAddress());
    quoteApiDto.sex = toGender(insurancePolicy.getIdentity().gender);
    quoteApiDto.numberOfTenants = new BigDecimal(insurancePolicy.getApartmentDetails().getNbOfHousing());
    quoteApiDto.withBusiness = insurancePolicy.getApartmentDetails().getHasCommerce();
    quoteApiDto.withCentralAlarm = insurancePolicy.getApartmentDetails().getHasAlarm();
    quoteApiDto.withSprinkler = insurancePolicy.getApartmentDetails().getHasJet();
    quoteApiDto.insuredAmount = insurancePolicy.getInsuranceAmount().amount.intValue();
    quoteApiDto.effectiveDate = insurancePolicy.getEffectiveDate().toString();
    return quoteApiDto;
  }

  public QuoteApiDto quoteToDao(InsurancePolicy insurancePolicy, Money newInsuranceAmount) {
    QuoteApiDto quoteApiDto = new QuoteApiDto();
    quoteApiDto.birthdate = insurancePolicy.getIdentity().birthday;
    quoteApiDto.name = insurancePolicy.getIdentity().name;
    quoteApiDto.address = addressToApiAssembler.toDao(insurancePolicy.getAddress());
    quoteApiDto.sex = toGender(insurancePolicy.getIdentity().gender);
    quoteApiDto.numberOfTenants = new BigDecimal(insurancePolicy.getApartmentDetails().getNbOfHousing());
    quoteApiDto.withBusiness = insurancePolicy.getApartmentDetails().getHasCommerce();
    quoteApiDto.withCentralAlarm = insurancePolicy.getApartmentDetails().getHasAlarm();
    quoteApiDto.withSprinkler = insurancePolicy.getApartmentDetails().getHasJet();
    quoteApiDto.insuredAmount = newInsuranceAmount != null ? newInsuranceAmount.amount.intValue() : insurancePolicy.getInsuranceAmount().amount.intValue();
    quoteApiDto.effectiveDate = insurancePolicy.getEffectiveDate().toString();
    return quoteApiDto;
  }

  public BigDecimal toBig(String num) {
    int i = Integer.parseInt(num);
    return new BigDecimal(i);
  }

  public Gender toGender(String sex) {
    if (sex.equals("male")) {
      return Gender.M;
    } else if (sex.equals("female")) {
      return Gender.F;
    } else {
      return Gender.I;
    }
  }

}
