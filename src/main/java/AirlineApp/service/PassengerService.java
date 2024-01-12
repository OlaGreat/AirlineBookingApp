package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.Passenger;
import AirlineApp.data.models.User;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.UserNotFoundException;

import java.util.List;

public interface PassengerService {
    RegisterPassengerResponse registerPassenger(RegisterPassengerRequest registerPassengerRequest);

    List<FlightSchedule> searchForFlight(FlightSearchRequest searchRequest) throws InvalidDateException;

    User findByEmail(String email) throws UserNotFoundException;

//    List<FlightSchedule> searchForFlightExtend(FlightSearchRequest searchRequest) throws InvalidDateException;

}
