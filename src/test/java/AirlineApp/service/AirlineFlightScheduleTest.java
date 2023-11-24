package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.AirlineException;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.ScheduleNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
public class AirlineFlightScheduleTest {
    @Autowired
    private FlightScheduleService flightScheduleService;


    @Test
    void testThatFlightCanBeScheduled() throws AirlineException {
        TripScheduleRequest request = buildTripScheduleRequest();
        FlightSchedule flightSchedule = flightScheduleService.scheduleTrip(request,1L);
        assertThat(flightSchedule).isNotNull();
    }

    @Test
    void testThatFlightScheduledCanBeSearchFor() throws InvalidDateException {
        FlightSearchRequest searchRequest = new FlightSearchRequest();
        searchRequest.setTakeOffDay("30");
        searchRequest.setTakeOffMonth("November");
        searchRequest.setTakeOffYear("2023");

        List<FlightSchedule> foundFlight = flightScheduleService.searchForFlight(searchRequest);
        foundFlight.forEach(System.out::println);

        assertThat(foundFlight.size()).isGreaterThan(0);
    }

    @Test
    void testThatFlightCanBeSearchWithAdditionalDays(){
        FlightSearchRequest request = new FlightSearchRequest();
        request.setTakeOffDay("30");
        request.setTakeOffMonth("November");
        request.setTakeOffYear("2023");

        List<FlightSchedule> foundFlight = flightScheduleService.searchForFlightModified(request);
        foundFlight.forEach(System.out::println);

        assertThat(foundFlight.size()).isGreaterThan(0);
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
        request.setTakeOffDay("30");
        request.setTakeOffYear("2023");
        request.setTakeOffTime("17:55");
        request.setLandingDay("12");
        request.setLandingMonth("November");
        request.setLandingYear("2023");
        request.setLandingTime("17:30");
        return request;
    }

}
