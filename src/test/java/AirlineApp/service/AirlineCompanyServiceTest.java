package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.*;
import AirlineApp.exceptions.AirlineException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class AirlineCompanyServiceTest {

    @Autowired
    private CompanyService airlineCompanyService;

    @Test
    void testThatCompanyCanRegister() throws AirlineException {
        CompanyRegistrationRequest request = new CompanyRegistrationRequest();
        request.setCompanyName("Speed Air");
        request.setCompanyLicencesNumber("537825ti9hg72Air");
        request.setLocation("Nigeria");
        request.setRoutes(List.of("Australia","Nigeria","usa"));
        CompanyRegistrationResponse registeredCompany = airlineCompanyService.registerCompany(request);

        assertThat(registeredCompany).isNotNull();
        log.info(String.valueOf(registeredCompany));
    }

    @Test
    void testThatFlightCanBeAddedToCompanyFleet() throws AirlineException {
        FlightRegistrationResponse flightRegistrationResponse = airlineCompanyService.addFlight(buildFlightRequest(), 1L);
        assertThat(flightRegistrationResponse).isNotNull();

    }

    @Test
    void testThatCompanyCanRemoveFlightByFlightName() throws AirlineException {
        FlightRemoveResponse response = airlineCompanyService.removeFlight("2351Y17");
        assertThat(response).isNotNull();
    }

    @Test
    void testThatCompanyCanViewListOfThereSchedule() throws AirlineException {
        List<FlightSchedule> scheduledFlight = airlineCompanyService.viewScheduledFlight(1L);
        assertThat(scheduledFlight.size()).isEqualTo(0);
    }
    @Test
    void testThatAirlineCompanyCanScheduleFlight() throws AirlineException {
        TripScheduleRequest request = buildTripScheduleRequest();
        TripScheduleResponse response = airlineCompanyService.scheduleFlightTrip(request,1L);
        assertThat(response).isNotNull();
        System.out.println(response);
    }

    @Test
    void testThatCompanyCanViewListOfThereScheduleAfterAdding() throws AirlineException {
        List<FlightSchedule> scheduledFlight = airlineCompanyService.viewScheduledFlight(1L);
        assertThat(scheduledFlight.size()).isEqualTo(2);
    }

    @Test void testThatCompanyCanDeleteFlightSchedule() throws AirlineException {
        DeleteScheduledFlightResponse response = airlineCompanyService.deleteScheduleFlight(1,602);
        assertThat(response).isNotNull();

    }

    @Test void testFindFirst(){
        Company company = airlineCompanyService.findFirstOne();
        System.out.println(company);
    }


    private AddFlightRequest buildFlightRequest(){
        AddFlightRequest request = new AddFlightRequest();
        request.setFlightName("Ro");
        request.setFlightCapacity(280);
        request.setFlightNumber("2351Y17");
        request.setDestination(List.of("USA","NIGERIA", "CANADA"));
        return request;

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


















