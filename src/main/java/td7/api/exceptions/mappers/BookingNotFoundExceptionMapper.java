package td7.api.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import td7.api.exceptions.ErrorResponse;
import td7.domain.cruise.booking.exceptions.BookingNotFoundException;

@Provider
public class BookingNotFoundExceptionMapper implements ExceptionMapper<BookingNotFoundException> {

    @Override
    public Response toResponse(BookingNotFoundException exception) {
        ErrorResponse error = new ErrorResponse("BOOKING_NOT_FOUND", exception.getMessage());
        return Response.status(404).entity(error).build();
    }
}
