package AirlineApp.service;

import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.response.AddFlightResponse;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.exceptions.AirlineException;

public interface CompanyService {
    CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest);
    AddFlightResponse addFlight(AddFlightRequest addFlightRequest) throws AirlineException;
}
