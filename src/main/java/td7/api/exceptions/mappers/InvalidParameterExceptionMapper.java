package td7.api.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import td7.api.exceptions.ErrorResponse;
import td7.domain.exceptions.InvalidParameterException;

@Provider
public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {

    @Override
    public Response toResponse(InvalidParameterException exception) {
        ErrorResponse error = new ErrorResponse("INVALID_PARAMETER", exception.getMessage());
        return Response.status(400).entity(error).build();
    }
}
