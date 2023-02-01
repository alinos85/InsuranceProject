package ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling;

import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.assembler.DoesNotExistExceptionAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.dto.ErrorMessageDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ApiException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<ApiException> {

  @Override
  public Response toResponse(ApiException apiException) {
    ErrorMessageDTO errorMessageDTO = new DoesNotExistExceptionAssembler().toDTO(apiException);
    return Response.status(Response.Status.BAD_REQUEST).entity(errorMessageDTO)
        .build();

  }
}
