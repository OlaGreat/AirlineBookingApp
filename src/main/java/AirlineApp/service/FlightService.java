package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Flight;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.exceptions.FlightNotFoundException;

public interface FlightService {
    FlightRegistrationResponse registerFlight(FlightRegistrationRequest flightRegistrationRequest, Company company);
    void deleteFlight(String flightNumber) throws FlightNotFoundException;


    Flight findByFlightNumber(String flightNumber) throws FlightNotFoundException;
}
