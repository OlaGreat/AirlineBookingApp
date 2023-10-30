package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;

public interface FlightService {
    FlightRegistrationResponse registerFlight(FlightRegistrationRequest flightRegistrationRequest, Company company);
}
