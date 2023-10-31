package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.exceptions.AirlineException;

public interface CompanyService {
    CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException;
    FlightRegistrationResponse addFlight(AddFlightRequest addFlightRequest, Long id) throws AirlineException;
    Company findById(Long id) throws AirlineException;

}
