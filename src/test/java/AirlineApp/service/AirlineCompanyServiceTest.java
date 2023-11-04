package AirlineApp.service;

import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightType;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.dtos.response.FlightRemoveResponse;
import AirlineApp.dtos.response.TripScheduleResponse;
import AirlineApp.exceptions.AirlineException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
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
        request.setCompanyName("JAPA Air");
        request.setCompanyLicencesNumber("537825tyhg72Air");
        request.setLocation("Nigeria");
        request.setRoutes(List.of(Destination.valueOf("AUSTRALIA"), Destination.valueOf("NIGERIA"),
                Destination.valueOf("USA")));
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
    void testThatAirlineCompanyCanScheduleFlight() throws AirlineException {
        TripScheduleRequest request = buildTripScheduleRequest();
        TripScheduleResponse response = airlineCompanyService.scheduleFlightTrip(request,1L);
        assertThat(response).isNotNull();
        System.out.println(response);
    }


    public AddFlightRequest buildFlightRequest(){
        AddFlightRequest request = new AddFlightRequest();
        request.setFlightName("Ro");
        request.setFlightCapacity(280);
        request.setFlightNumber("2351Y17");
        request.setDestination(List.of(Destination.valueOf("USA"),
                Destination.valueOf("NIGERIA"), Destination.valueOf("CANADA")));
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


















