package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.AirlineException;
import AirlineApp.exceptions.ScheduleNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AirlineFlightScheduleTest {
    @Autowired
    private FlightScheduleService flightScheduleService;


    @Test
    void testThatFlightCanScheduleFlight() throws AirlineException {
        TripScheduleRequest request = buildTripScheduleRequest();
        FlightSchedule flightSchedule = flightScheduleService.scheduleTrip(request,1L);
        assertThat(flightSchedule).isNotNull();
    }

    @Test
    void testThatScheduleFlightCanBeDeleted() throws ScheduleNotFoundException {
        FlightSchedule foundFlightSchedule = flightScheduleService.findById(302);
        FlightSchedule deletedFlight = flightScheduleService.deleteScheduledFlight(302);
        assertThat(deletedFlight).isNotNull();
        assertThat(deletedFlight).isEqualTo(foundFlightSchedule);
    }

    private static TripScheduleRequest buildTripScheduleRequest(){
        TripScheduleRequest request = new TripScheduleRequest();
        request.setDestination("CANADA");
        request.setStartLocation("LAGOS");
        request.setFlightType("DIRECT");
        request.setFlightCapacity(280);
        request.setFlightName("Ro");
        request.setFlightPriceEconomyClass("380");
        request.setFlightPriceBusinessClass("700");
        request.setTakeOffMonth("November");
        request.setTakeOffDay("11");
        request.setTakeOffYear("2023");
        request.setTakeOffTime("22:30");
        request.setLandingDay("12");
        request.setLandingMonth("November");
        request.setLandingYear("2023");
        request.setLandingTime("17:30");
        return request;
    }

}
