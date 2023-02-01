package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.Message;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;

public class EmailRenewalEffectiveDate extends EmailMecanism implements Message {

  private final InsurancePolicy insurancePolicy;

  public EmailRenewalEffectiveDate(InsurancePolicy insurancePolicy) {
    this.insurancePolicy = insurancePolicy;
  }

  @Override
  public Messageable getMessageable() {
    return insurancePolicy.getIdentity();
  }

  @Override
  public String getBody() {
    String htmlString = mecanismEmail("templateRenewalContractStart.html");
    htmlString = htmlString.replace("$name", insurancePolicy.getIdentity().name);
    htmlString = htmlString.replace("$price", insurancePolicy.getPrice().toString());
    htmlString = htmlString.replace("$id", insurancePolicy.getId().value);
    htmlString = htmlString.replace("$expireDate", insurancePolicy.getExpirationDate().toString());

    return htmlString;
  }

  @Override
  public String getSubject() {
    return "Renewal Insurance Policy";
  }

}