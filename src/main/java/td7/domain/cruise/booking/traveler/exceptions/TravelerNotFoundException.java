package td7.domain.cruise.booking.traveler.exceptions;

public class TravelerNotFoundException extends RuntimeException {
    public TravelerNotFoundException() {
        super("Traveler not found");
    }
}
