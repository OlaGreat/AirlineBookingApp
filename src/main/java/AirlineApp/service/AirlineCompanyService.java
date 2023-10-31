package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.repositories.CompanyRepository;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.exceptions.AirlineException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import static AirlineApp.dtos.response.ResponseMessage.WELCOME_ONBOARD;
import static AirlineApp.dtos.response.ResponseMessage.WE_LOOK_FORWARD_TO_A_WONDERFUL_PARTNERSHIP;
import static AirlineApp.exceptions.ExceptionMessages.*;


@Service
@AllArgsConstructor
public class AirlineCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final FlightService flightService;

    @Override
    public CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException {
        verifyCompanyRegistrationRequest(companyRegistrationRequest);
        Company company = new Company();
        BeanUtils.copyProperties(companyRegistrationRequest,company);
        Company savedCompany = companyRepository.save(company);

        CompanyRegistrationResponse companyRegistrationResponse = new CompanyRegistrationResponse();
        companyRegistrationResponse.setMessage(WELCOME_ONBOARD.getMessage()+savedCompany.getCompanyName()+WE_LOOK_FORWARD_TO_A_WONDERFUL_PARTNERSHIP.getMessage());
        return companyRegistrationResponse;
    }

    @Override
    public FlightRegistrationResponse addFlight(AddFlightRequest addFlightRequest, Long id) throws AirlineException {
        FlightRegistrationRequest flightRegistrationRequest = new FlightRegistrationRequest();
        BeanUtils.copyProperties(addFlightRequest, flightRegistrationRequest);

        Company company = findById(id);
        FlightRegistrationResponse flightRegistrationResponse = flightService.registerFlight(flightRegistrationRequest, company);
        return flightRegistrationResponse;
    }


    @Override
    public Company findById(Long id) throws AirlineException {
        Company company = companyRepository.findById(id)
                                           .orElseThrow(()->new AirlineException(COMPANY_NOT_FOUND.getMessage()));
        return company;
    }

    private static void verifyCompanyRegistrationRequest(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException {
        if(companyRegistrationRequest.getCompanyName() == null)throw new AirlineException(PLEASE_PROVIDE_YOUR_COMPANY_NAME.getMessage());
        if (companyRegistrationRequest.getCompanyLicencesNumber() == null) throw new AirlineException(PLEASE_PROVIDE_YOUR_LICENCES_NUMBER.getMessage());
    }
}
