package ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.dto.ErrorMessageDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;

public class DoesNotExistExceptionAssembler {

  public ErrorMessageDTO toDTO(ApiException apiException) {
    ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
    errorMessageDTO.error = apiException.getError();
    errorMessageDTO.errorMessage = apiException.getMessage();
    return errorMessageDTO;
  }
}
