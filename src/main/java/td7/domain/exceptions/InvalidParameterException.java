package td7.domain.exceptions;

public class InvalidParameterException extends IllegalArgumentException {

    public InvalidParameterException(String message) {
        super(message);
    }
}
