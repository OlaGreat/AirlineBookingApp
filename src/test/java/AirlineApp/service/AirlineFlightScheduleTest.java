package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.TripScheduleResponse;
import AirlineApp.exceptions.AirlineException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AirlineFlightScheduleTest {
    @Autowired
    private FlightScheduleService flightScheduleService;

    @Autowired
    private CompanyService companyService;

    @Test
    void testThatFlightCanScheduleFlight() throws AirlineException {
        Company company = companyService.findById(1L);
        TripScheduleRequest request = buildTripScheduleRequest();
        FlightSchedule flightSchedule = flightScheduleService.scheduleTrip(request,1L);
        assertThat(flightSchedule).isNotNull();
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
