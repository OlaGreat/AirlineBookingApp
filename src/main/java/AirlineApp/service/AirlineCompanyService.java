package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Flight;
import AirlineApp.data.repositories.CompanyRepository;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.response.AddFlightResponse;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.exceptions.AirlineException;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static AirlineApp.data.models.FlightDestination.*;


@Service
@AllArgsConstructor
public class AirlineCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest) {
        verifyCompanyRegistrationRequest(companyRegistrationRequest);
        Company company = new Company();
        BeanUtils.copyProperties(companyRegistrationRequest,company);
        Company savedCompany = companyRepository.save(company);

        CompanyRegistrationResponse companyRegistrationResponse = new CompanyRegistrationResponse();
        companyRegistrationResponse.setMessage("Welcome onboard "+savedCompany.getCompanyName()+", we look forward to a wonderful" +
                "partnerShip");

        return companyRegistrationResponse;
    }

    @Override
    public AddFlightResponse addFlight(AddFlightRequest addFlightRequest) throws AirlineException {
        String companyName = addFlightRequest.getCompanyName();
        Flight flight = new Flight();
        flight.setFlightDestinations(List.of(USA,NIGERIA,CANADA));
        BeanUtils.copyProperties(addFlightRequest, flight);
        Optional<Company> company = companyRepository.readByCompanyName(companyName);

        if(company.isPresent()){
        Company foundCompany = company.get();
        foundCompany.setFleet(List.of(flight));
        companyRepository.save(foundCompany);
        }else throw new AirlineException("Company Does Not Exist Please");

        AddFlightResponse response = new AddFlightResponse();
        response.setMessage("Flight "+ addFlightRequest.getFlightName() +" Successfully added to "+ addFlightRequest.getCompanyName()
                + " Fleet");
        return response;
    }

    private static void verifyCompanyRegistrationRequest(CompanyRegistrationRequest companyRegistrationRequest) {
        if(companyRegistrationRequest.getCompanyName() == null)throw new IllegalArgumentException("Please provide a your company name");
        if (companyRegistrationRequest.getCompanyLicencesNumber() == null) throw new IllegalArgumentException("Please Provide  your Company Licence Number");
    }
}
