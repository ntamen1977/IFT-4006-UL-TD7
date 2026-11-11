package td7.application.cruise.booking;

import jakarta.inject.Inject;
import td7.application.cruise.booking.dtos.BookingDto;
import td7.application.cruise.booking.dtos.BookingIdDto;
import td7.application.cruise.booking.dtos.newBooking.NewBookingDto;
import td7.application.utils.dateTimeParser.LocalDateTimeParser;
import td7.domain.cruise.Cruise;
import td7.domain.cruise.CruiseId;
import td7.domain.cruise.CruiseRepository;
import td7.domain.cruise.booking.Booking;
import td7.domain.cruise.booking.BookingFactory;
import td7.domain.cruise.booking.BookingId;
import td7.domain.cruise.booking.exceptions.BookingNotFoundException;
import td7.domain.cruise.booking.newBooking.NewBooking;
import td7.domain.cruise.exceptions.CruiseNotFoundException;

import java.time.LocalDateTime;

public class BookingService {

    private final CruiseRepository cruiseRepository;
    private final BookingFactory bookingFactory;
    private final BookingAssembler bookingAssembler;
    private final LocalDateTimeParser localDateTimeParser;

    @Inject
    public BookingService(CruiseRepository cruiseRepository, BookingFactory bookingFactory, BookingAssembler bookingAssembler,
                          LocalDateTimeParser localDateTimeParser) {
        this.cruiseRepository = cruiseRepository;
        this.bookingFactory = bookingFactory;
        this.bookingAssembler = bookingAssembler;
        this.localDateTimeParser = localDateTimeParser;
    }

    public BookingIdDto createBooking(String cruiseId, NewBookingDto newBookingDto) {
        Cruise cruise = findCruiseById(cruiseId);

        LocalDateTime bookingDateTime = localDateTimeParser.parse(newBookingDto.bookingDateTime);

        NewBooking newBooking = bookingAssembler.toNewBooking(newBookingDto, bookingDateTime);
        Booking booking = bookingFactory.create(newBooking);

        cruise.processBooking(booking);
        cruiseRepository.save(cruise);

        return new BookingIdDto(booking.getId().toString());
    }

    public BookingDto findBooking(String cruiseId, String bookingIdStr) {
        Cruise cruise = findCruiseById(cruiseId);

        BookingId bookingId = new BookingId(bookingIdStr);
        Booking booking = cruise.findBookingById(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException();
        }

        return bookingAssembler.toDto(cruiseId, booking);
    }

    private Cruise findCruiseById(String cruiseId) {
        Cruise cruise = cruiseRepository.findById(new CruiseId(cruiseId));
        if (cruise == null) {
            throw new CruiseNotFoundException();
        }
        return cruise;
    }
}
