package ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling;

import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.assembler.DoesNotExistExceptionAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.dto.ErrorMessageDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.common.exception.ResourceConflitException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ResourceConflictExceptionMapper implements ExceptionMapper<ResourceConflitException> {

  @Override
  public Response toResponse(ResourceConflitException apiException) {
    ErrorMessageDTO errorMessageDTO = new DoesNotExistExceptionAssembler().toDTO(apiException);
    return Response.status(Response.Status.CONFLICT).entity(errorMessageDTO)
        .build();

  }
}
