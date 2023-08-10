package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class AirlineCompanyServiceTest {

    @Autowired
    private CompanyService airlineCompanyService;

    @Test
    void testThatCompanyCanRegister(){
        CompanyRegistrationRequest request = new CompanyRegistrationRequest();
        request.setCompanyName("JAPA Air");
        request.setCompanyLicencesNumber("537825tyhg72Air");
        CompanyRegistrationResponse registeredCompany = airlineCompanyService.registerCompany(request);

        assertThat(registeredCompany).isNotNull();
        log.info(String.valueOf(registeredCompany));
    }

    @Test
    void testThatFlightCanBeAddedToCompanyFleet(){
        
    }

}