package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;

import java.util.List;

public interface PassengerService {
    RegisterPassengerResponse registerPassenger(RegisterPassengerRequest registerPassengerRequest);

    List<FlightSchedule> searchForFlight(FlightSearchRequest searchRequest);
}
