package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.Gender;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;
import AirlineApp.exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PassengerServiceTest {

    @Autowired
    private AirlinePassengerService passengerService;

    @Test
    public void passengerCanRegisterTest(){
        RegisterPassengerRequest registerPassenger = new RegisterPassengerRequest();
        registerPassenger.setFirstName("chinedu");
        registerPassenger.setLastName("ugbo");
        registerPassenger.setEmail("o.ugbochinedu@yahoo.com");
        registerPassenger.setGender(Gender.MALE);
        registerPassenger.setPhoneNumber("07035137163");
        registerPassenger.setPassword("123nder");
        RegisterPassengerResponse registerPassengerResponse = passengerService.registerPassenger(registerPassenger);
        assertNotNull(registerPassengerResponse);
    }


    @Test
    void testThatCustomerCanSearchForScheduledFlight() throws InvalidDateException {
        FlightSearchRequest searchRequest = buildFlightSearch();
           List<FlightSchedule> foundFlight = passengerService.searchForFlight(searchRequest);
        assertThat(foundFlight.size()).isGreaterThan(1);
    }

    private FlightSearchRequest buildFlightSearch() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setTakeOffYear("2023");
        request.setTakeOffMonth("November");
        request.setTakeOffDay("30");

        return request;
    }


//    @Test
//    void testThatPassengerCanBookFlight(){
//        BookingRequest request = BuildBookingRequest();
//        BookingResponse response = passengerService.bookFlight(request);
//    }

//    private BookingRequest BuildBookingRequest() {
//
//    }
}
