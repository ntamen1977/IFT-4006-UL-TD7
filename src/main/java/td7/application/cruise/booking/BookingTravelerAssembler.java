package td7.application.cruise.booking;

import td7.application.cruise.booking.dtos.BookingTravelerDto;
import td7.application.cruise.booking.dtos.newBooking.NewBookingTravelerDto;
import td7.domain.cruise.booking.newBooking.NewBookingTraveler;
import td7.domain.cruise.booking.traveler.Traveler;

public class BookingTravelerAssembler {
    public BookingTravelerDto toDto(Traveler traveler) {
        return new BookingTravelerDto(
                traveler.getId().toString(),
                traveler.getName().toString(),
                traveler.getCategory().toString());
    }

    public NewBookingTraveler toNewBookingTraveler(NewBookingTravelerDto newBookingTravelerDto) {
        return new NewBookingTraveler(newBookingTravelerDto.name, newBookingTravelerDto.category);
    }
}
