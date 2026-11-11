package td7.domain.cruise.booking.exceptions;

import td7.domain.exceptions.InvalidParameterException;

public class InvalidBookingDateException extends InvalidParameterException {

    public InvalidBookingDateException() {
        super("Invalid booking date.");
    }
}
