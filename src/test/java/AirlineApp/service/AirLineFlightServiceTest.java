package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Destination;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.exceptions.AirlineException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AirLineFlightServiceTest {

    @Autowired
    private FlightService flightService;
    @Autowired
    private CompanyService companyService;

    @Test
    void testRegisterFlight() throws AirlineException {
        Company company = companyService.findById(1L);
        FlightRegistrationResponse response = flightService.registerFlight(buildFlightRegistration(),company);
        assertThat(response).isNotNull();
    }

    private FlightRegistrationRequest buildFlightRegistration(){
        FlightRegistrationRequest request = new FlightRegistrationRequest();
        request.setFlightName("Ro");
        request.setFlightCapacity(280);
        request.setFlightNumber("2351Y17");
        request.setDestination(List.of(Destination.valueOf("USA"),
                Destination.valueOf("NIGERIA"), Destination.valueOf("CANADA")));

        return request;

    }
}