package ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.dto.ErrorMessageDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.ClaimContractNotAcceptedException;

public class ClaimContractNotAcceptedExceptionAssembler {

  public ErrorMessageDTO toDTO(ClaimContractNotAcceptedException claimContractNotAcceptedException) {
    ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
    errorMessageDTO.error = claimContractNotAcceptedException.ERROR;
    errorMessageDTO.errorMessage = claimContractNotAcceptedException.getMessage();
    return errorMessageDTO;
  }
}
