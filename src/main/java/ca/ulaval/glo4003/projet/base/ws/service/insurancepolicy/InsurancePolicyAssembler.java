package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;

public class InsurancePolicyAssembler {

  public InsurancePolicyResponseDTO create(InsurancePolicy insurancePolicy) {
    InsurancePolicyResponseDTO insurancePolicyResponseDTO = new InsurancePolicyResponseDTO();
    insurancePolicyResponseDTO.insurancePolicyId = insurancePolicy.getId().value;

    return insurancePolicyResponseDTO;
  }

}
