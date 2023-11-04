package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.TripScheduleResponse;

public interface FlightScheduleService {
    TripScheduleResponse scheduleTrip(TripScheduleRequest tripScheduleRequest, Company company);
}
