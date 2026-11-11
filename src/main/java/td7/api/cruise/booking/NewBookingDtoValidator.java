package td7.api.cruise.booking;

import td7.application.cruise.booking.dtos.newBooking.NewBookingDto;
import td7.domain.exceptions.MissingParameterException;

public class NewBookingDtoValidator {
    public void validate(NewBookingDto newBookingDto) {
        if (newBookingDto.bookingDateTime == null) {
            throw new MissingParameterException("bookingDate");
        }
        if (newBookingDto.cabinType == null) {
            throw new MissingParameterException("cabinType");
        }
        if (newBookingDto.travelers == null) {
            throw new MissingParameterException("travelers");
        }
        newBookingDto.travelers.forEach(traveler -> {
            if (traveler.name == null) {
                throw new MissingParameterException("name");
            }
            if (traveler.category == null) {
                throw new MissingParameterException("category");
            }
        });
    }
}
