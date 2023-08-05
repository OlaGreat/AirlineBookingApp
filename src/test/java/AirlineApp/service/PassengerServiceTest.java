package AirlineApp.service;

import AirlineApp.data.models.Gender;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
