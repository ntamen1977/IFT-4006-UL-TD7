package td7.domain.cruise.cabin.exceptions;

import td7.domain.exceptions.InvalidParameterException;

public class InvalidCabinTypeException extends InvalidParameterException {

    public InvalidCabinTypeException() {
        super("Invalid cabin type.");
    }
}
