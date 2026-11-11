package td7.domain.cruise;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import td7.domain.cruise.booking.Booking;
import td7.domain.cruise.booking.BookingId;
import td7.domain.cruise.booking.CruiseBookings;
import td7.domain.cruise.cabin.CabinAvailabilities;
import td7.domain.cruise.dateTime.CruiseDateTime;

@Entity
@Table(name = "Cruise")
public class Cruise {
    @Id
    private CruiseId id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "localDateTime",
                    column = @Column(name = "departureDateTime")
            ),
    })
    private CruiseDateTime departureDateTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "localDateTime",
                    column = @Column(name = "endDateTime")
            ),
    })
    private CruiseDateTime endDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private CabinAvailabilities cabinAvailabilities;

    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private CruiseBookings cruiseBookings;

    public Cruise(CruiseId id, CruiseDateTime departureDateTime, CruiseDateTime endDateTime, CabinAvailabilities cabinAvailabilities,
                  CruiseBookings cruiseBookings) {
        this.id = id;
        this.departureDateTime = departureDateTime;
        this.endDateTime = endDateTime;
        this.cabinAvailabilities = cabinAvailabilities;
        this.cruiseBookings = cruiseBookings;
    }

    public Cruise() {

    }

    public CruiseId getId() {
        return id;
    }

    public void processBooking(Booking booking) {
        booking.validate(departureDateTime);
        cabinAvailabilities.bookCabin(booking.getCabinType());
        cruiseBookings.add(booking);
    }

    public Booking findBookingById(BookingId bookingId) {
        return cruiseBookings.findById(bookingId);
    }
}
