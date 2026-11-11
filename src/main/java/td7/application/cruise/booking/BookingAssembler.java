package td7.application.cruise.booking;

import jakarta.inject.Inject;
import td7.application.cruise.booking.dtos.BookingDto;
import td7.application.cruise.booking.dtos.BookingTravelerDto;
import td7.application.cruise.booking.dtos.newBooking.NewBookingDto;
import td7.application.cruise.booking.dtos.newBooking.NewBookingTravelerDto;
import td7.domain.cruise.booking.Booking;
import td7.domain.cruise.booking.newBooking.NewBooking;
import td7.domain.cruise.booking.newBooking.NewBookingTraveler;
import td7.domain.cruise.booking.traveler.Traveler;

import java.time.LocalDateTime;
import java.util.List;

public class BookingAssembler {

    private final BookingTravelerAssembler bookingTravelerAssembler;

    @Inject
    public BookingAssembler(BookingTravelerAssembler bookingTravelerAssembler) {
        this.bookingTravelerAssembler = bookingTravelerAssembler;
    }

    public BookingDto toDto(String cruiseId, Booking booking) {
        List<BookingTravelerDto> travelerDtos = createBookingTravelerDtos(booking.getTravelers());

        return new BookingDto(
                    cruiseId,
                    booking.getId().toString(),
                    travelerDtos,
                    booking.getCabinType().toString(),
                    booking.getBookingDateTime().toString());
    }

    public NewBooking toNewBooking(NewBookingDto newBookingDto, LocalDateTime bookingDateTime) {
        List<NewBookingTraveler> newBookingTravelers = createNewBookingTravelers(newBookingDto.travelers);
        return new NewBooking(newBookingDto.cabinType, newBookingTravelers, bookingDateTime);
    }

    private List<BookingTravelerDto> createBookingTravelerDtos(List<Traveler> travelers) {
        return travelers.stream().map(bookingTravelerAssembler::toDto).toList();
    }

    private List<NewBookingTraveler> createNewBookingTravelers(List<NewBookingTravelerDto> travelers) {
        return travelers.stream().map(bookingTravelerAssembler::toNewBookingTraveler).toList();
    }
}
