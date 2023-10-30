package AirlineApp.service;

import AirlineApp.data.models.Destination;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.dtos.response.FlightRegistrationResponse;
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
        request.setCompanyName("JAPA Air");
        request.setCompanyLicencesNumber("537825tyhg72Air");
        CompanyRegistrationResponse registeredCompany = airlineCompanyService.registerCompany(request);

        assertThat(registeredCompany).isNotNull();
        log.info(String.valueOf(registeredCompany));
    }

    @Test
    void testThatFlightCanBeAddedToCompanyFleet() throws AirlineException {
        FlightRegistrationResponse flightRegistrationResponse = airlineCompanyService.addFlight(buildFlightRequest(), 1L);
        assertThat(flightRegistrationResponse).isNotNull();

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

}


















