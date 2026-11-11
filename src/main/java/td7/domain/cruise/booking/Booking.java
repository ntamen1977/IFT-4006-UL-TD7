package td7.domain.cruise.booking;

import jakarta.persistence.*;
import td7.domain.cruise.booking.exceptions.InvalidBookingDateException;
import td7.domain.cruise.booking.traveler.Traveler;
import td7.domain.cruise.cabin.CabinType;
import td7.domain.cruise.dateTime.CruiseDateTime;
import td7.domain.cruise.exceptions.NoTravelerToBookException;

import java.util.List;

@Entity
public class Booking {
    @Id
    private BookingId id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Traveler> travelers;

    @Enumerated(EnumType.STRING)
    private CabinType cabinType;

    @Embedded
    private CruiseDateTime bookingDateTime;

    public Booking(BookingId id, List<Traveler> travelers, CabinType cabinType, CruiseDateTime bookingDateTime) {
        this.id = id;
        this.travelers = travelers;
        this.cabinType = cabinType;
        this.bookingDateTime = bookingDateTime;
    }

    public Booking() {

    }

    public BookingId getId() {
        return id;
    }

    public CabinType getCabinType() {
        return cabinType;
    }

    public List<Traveler> getTravelers() {
        return travelers;
    }

    public CruiseDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void validate(CruiseDateTime cruiseDepartureDateTime) {
        if (travelers.isEmpty()) {
            throw new NoTravelerToBookException();
        }

        if (!bookingDateIsBeforeCruiseDeparture(cruiseDepartureDateTime)) {
            throw new InvalidBookingDateException();
        }
    }

    private boolean bookingDateIsBeforeCruiseDeparture(CruiseDateTime cruiseDepartureDateTime) {
        return bookingDateTime.isBefore(cruiseDepartureDateTime);
    }
}
