package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.ScheduleNotFoundException;

import java.util.List;

public interface FlightScheduleService {
    FlightSchedule scheduleTrip(TripScheduleRequest tripScheduleRequest, long companyId) throws InvalidDateException;
//    FlightCancellationResponse cancelScheduledFlight(FlightCancellationRequest flightCancellationRequest);
    void deleteScheduledFlight(FlightSchedule flightScheduleToBeDeleted);
    FlightSchedule findById(long scheduleId) throws ScheduleNotFoundException;

    List<FlightSchedule> searchForFlight (FlightSearchRequest searchRequest);
}
