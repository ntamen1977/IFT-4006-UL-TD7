package td7.domain.cruise.booking;

import jakarta.inject.Inject;
import td7.domain.cruise.booking.newBooking.NewBooking;
import td7.domain.cruise.booking.newBooking.NewBookingTraveler;
import td7.domain.cruise.booking.traveler.Traveler;
import td7.domain.cruise.booking.traveler.TravelerCategory;
import td7.domain.cruise.booking.traveler.TravelerFactory;
import td7.domain.cruise.cabin.CabinType;
import td7.domain.cruise.dateTime.CruiseDateTime;

import java.util.List;

public class BookingFactory {
    private final TravelerFactory travelerFactory;

    @Inject
    public BookingFactory(TravelerFactory travelerFactory) {
        this.travelerFactory = travelerFactory;
    }

    public Booking create(NewBooking newBooking) {
        List<Traveler> travelers = createTravelers(newBooking.travelers());
        CabinType cabinType = CabinType.fromString(newBooking.cabinType());
        CruiseDateTime bookingDateTime = new CruiseDateTime(newBooking.bookingDateTime());

        return new Booking(new BookingId(), travelers, cabinType, bookingDateTime);
    }

    private List<Traveler> createTravelers(List<NewBookingTraveler> travelers) {
        return travelers.stream().map(traveler -> travelerFactory.create(traveler.name(), TravelerCategory.fromString(traveler.category()))).toList();
    }
}
