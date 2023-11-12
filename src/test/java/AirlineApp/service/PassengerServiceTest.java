package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.Gender;
import AirlineApp.dtos.request.BookingRequest;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void testThatCustomerCanSearchForScheduledFlight(){
        FlightSearchRequest searchRequest = new FlightSearchRequest();

        List<FlightSchedule> foundFlight = passengerService.searchForFlight(searchRequest);
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
