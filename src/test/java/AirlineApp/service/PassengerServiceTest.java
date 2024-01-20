package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.Gender;
import AirlineApp.data.models.Passenger;
import AirlineApp.data.models.User;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
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
        request.setTakeOffMonth("December");
        request.setTakeOffDay(30);

        return request;
    }

    @Test
    @DisplayName("Find passenger by email")
    public void findPassengerByEmail() throws UserNotFoundException {
        User foundUser = passengerService.findByEmail("o.ugbochinedu@yahoo.com");
        assertThat(foundUser).isNotNull();
    }


//    @Test
//    void testThatPassengerCanBookFlight(){
//        BookingRequest request = BuildBookingRequest();
//        BookingResponse response = passengerService.bookFlight(request);
//   }

//    private BookingRequest BuildBookingRequest() {
//
//    }
}
