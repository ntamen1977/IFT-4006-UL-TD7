package td7.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import td7.api.cruise.booking.NewBookingDtoValidator;
import td7.application.utils.dateTimeParser.LocalDateTimeParser;
import td7.application.cruise.booking.BookingAssembler;
import td7.application.cruise.booking.BookingService;
import td7.application.cruise.booking.BookingTravelerAssembler;
import td7.domain.cruise.CruiseRepository;
import td7.domain.cruise.booking.BookingFactory;
import td7.domain.cruise.booking.traveler.TravelerFactory;
import td7.infra.persistence.hibernate.HibernateCruiseRepository;

import java.time.format.DateTimeFormatter;

public class ConfigurationServerRest extends AbstractBinder {

    @Override
    protected void configure() {
        bindAsContract(TravelerFactory.class);
        bindAsContract(BookingFactory.class);

        bindAsContract(BookingTravelerAssembler.class);
        bindAsContract(BookingAssembler.class);

        bind(DateTimeFormatter.ISO_LOCAL_DATE_TIME).to(DateTimeFormatter.class);
        bindAsContract(LocalDateTimeParser.class);

        bind(HibernateCruiseRepository.class).to(CruiseRepository.class);

        bindAsContract(NewBookingDtoValidator.class);

        bindAsContract(BookingService.class);
    }
}
