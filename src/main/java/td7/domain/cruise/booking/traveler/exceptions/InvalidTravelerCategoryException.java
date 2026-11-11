package td7.domain.cruise.booking.traveler.exceptions;

import td7.domain.exceptions.InvalidParameterException;

public class InvalidTravelerCategoryException extends InvalidParameterException {

    public InvalidTravelerCategoryException() {
        super("Invalid traveler category.");
    }
}
