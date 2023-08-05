package AirlineApp.service;

import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;

public interface PassengerService {
    RegisterPassengerResponse registerPassenger(RegisterPassengerRequest registerPassengerRequest);
}
