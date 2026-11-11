package td7;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import td7.api.ConfigurationServerRest;
import td7.domain.cruise.Cruise;
import td7.domain.cruise.CruiseId;
import td7.domain.cruise.CruiseRepository;
import td7.domain.cruise.booking.CruiseBookings;
import td7.domain.cruise.cabin.CabinAvailabilities;
import td7.domain.cruise.cabin.CabinType;
import td7.domain.cruise.dateTime.CruiseDateTime;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main implements Runnable {
    private static final int PORT = 8181;
    private static final String DEFAULT_CRUISE_ID = "ALASKA";
    private static final LocalDateTime DEFAULT_CRUISE_DEPARTURE_DATE = LocalDateTime.of(2085, 1, 25, 12, 0);
    private static final LocalDateTime DEFAULT_CRUISE_END_DATE = LocalDateTime.of(2085, 2, 1, 12, 0);
    private static final int DEFAULT_STANDARD_CABINS = 10;
    private static final int DEFAULT_DELUXE_CABINS = 150;
    private static final int DEFAULT_SUITE_CABINS = 100;

    private final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig packageConfig = new ResourceConfig()
                .packages("td7")
                .register(JacksonFeature.withoutExceptionMappers())
                .register(ConfigurationServerRest.class);
        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            logger.info("Server started on port " + PORT);

            CruiseRepository cruiseRepository = getCruiseRepositoryFromContainer(container);
            initializeCruiseRepositoryWithDefaultCruise(cruiseRepository);


            server.join();
        } catch (Exception e) {
            logger.error("Error starting server", e);
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }

    private CruiseRepository getCruiseRepositoryFromContainer(ServletContainer container) {
        return container.getApplicationHandler().getInjectionManager().getInstance(CruiseRepository.class);
    }

    private void initializeCruiseRepositoryWithDefaultCruise(CruiseRepository cruiseRepository) {
        Cruise cruise = new Cruise(new CruiseId(DEFAULT_CRUISE_ID),
                                   new CruiseDateTime(DEFAULT_CRUISE_DEPARTURE_DATE),
                                   new CruiseDateTime(DEFAULT_CRUISE_END_DATE),
                                   new CabinAvailabilities(new HashMap<>(
                                           Map.of(CabinType.STANDARD, DEFAULT_STANDARD_CABINS,
                                                  CabinType.DELUXE, DEFAULT_DELUXE_CABINS,
                                                  CabinType.SUITE, DEFAULT_SUITE_CABINS))),
                                   new CruiseBookings());

        cruiseRepository.save(cruise);
    }
}