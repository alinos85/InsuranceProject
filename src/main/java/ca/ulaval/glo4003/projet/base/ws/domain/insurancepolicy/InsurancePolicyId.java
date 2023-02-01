package ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy;

import java.util.UUID;

public class InsurancePolicyId {

  public String value;

  public InsurancePolicyId() {
    this.value = UUID.randomUUID().toString();
  }

  public InsurancePolicyId(String insurancePolicyId) {
    this.value = UUID.fromString(insurancePolicyId).toString();
  }
}
