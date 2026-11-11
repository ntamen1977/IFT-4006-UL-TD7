package td7.api.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import td7.api.exceptions.ErrorResponse;
import td7.domain.exceptions.InvalidDateFormatException;

@Provider
public class InvalidDateFormatExceptionMapper implements ExceptionMapper<InvalidDateFormatException> {

    @Override
    public Response toResponse(InvalidDateFormatException exception) {
        ErrorResponse error = new ErrorResponse("INVALID_DATE_FORMAT", exception.getMessage());
        return Response.status(400).entity(error).build();
    }
}
