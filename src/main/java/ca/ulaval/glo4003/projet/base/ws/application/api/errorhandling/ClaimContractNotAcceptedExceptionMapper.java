package ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling;

import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.assembler.ClaimContractNotAcceptedExceptionAssembler;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.dto.ErrorMessageDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.ClaimContractNotAcceptedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClaimContractNotAcceptedExceptionMapper implements ExceptionMapper<ClaimContractNotAcceptedException> {

  @Override
  public Response toResponse(ClaimContractNotAcceptedException claimContractNotAcceptedException) {
    ErrorMessageDTO errorMessageDTO = new ClaimContractNotAcceptedExceptionAssembler().toDTO(claimContractNotAcceptedException);
    return Response.status(Response.Status.BAD_REQUEST).entity(errorMessageDTO)
        .build();
  }
}
