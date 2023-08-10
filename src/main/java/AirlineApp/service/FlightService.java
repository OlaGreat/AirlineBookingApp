package AirlineApp.service;

import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;

public interface FlightService {
    FlightRegistrationResponse registerFlight(FlightRegistrationRequest flightRegistrationRequest);
}
