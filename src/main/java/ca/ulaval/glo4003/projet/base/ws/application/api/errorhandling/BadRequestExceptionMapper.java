package ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling;

import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.dto.ValidationErrorMessageDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadRequestExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    return Response.status(Response.Status.BAD_REQUEST)
        .entity(prepareMessage(exception))
        .build();
  }

  private ValidationErrorMessageDTO[] prepareMessage(ConstraintViolationException exception) {

    Map<String, String> errors = new HashMap<>();

    for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {

      //Le résultat de cv.getPropertyPath() est method.arg0.field
      //On tente de se débarasser de tout ce qui est avant le field
      String field = cv.getPropertyPath().toString();
      field = field.substring(field.indexOf(".", field.indexOf(".") + 1) + 1);

      errors.put(field, cv.getMessage());
    }

    List<ValidationErrorMessageDTO> errorMessages = new ArrayList<>();
    for (Map.Entry<String, String> error : errors.entrySet()) {
      ValidationErrorMessageDTO errorMessage = new ValidationErrorMessageDTO();
      errorMessage.field = error.getKey();
      errorMessage.error = error.getValue();

      errorMessages.add(errorMessage);
    }

    return errorMessages.toArray(new ValidationErrorMessageDTO[0]);
  }
}
