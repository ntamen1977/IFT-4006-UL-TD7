package td7.domain.cruise.booking;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class CruiseBookings {
    @Id
    private CruiseBookingsId id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapKey(name = "id")
    private Map<BookingId, Booking> bookings;

    public CruiseBookings() {
        id = new CruiseBookingsId();
        bookings = new HashMap<>();
    }

    public void add(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public Booking findById(BookingId bookingId) {
        return bookings.get(bookingId);
    }
}
