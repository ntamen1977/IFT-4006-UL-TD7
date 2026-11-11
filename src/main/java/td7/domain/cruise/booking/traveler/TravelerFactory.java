package td7.domain.cruise.booking.traveler;

public class TravelerFactory {
    public Traveler create(String travelerNameStr, TravelerCategory travelerCategory) {
        TravelerId travelerId = new TravelerId();
        TravelerName travelerName = new TravelerName(travelerNameStr);

        return new Traveler(travelerId, travelerName, travelerCategory);
    }
}
