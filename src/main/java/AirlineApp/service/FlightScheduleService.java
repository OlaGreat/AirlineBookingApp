package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.ScheduleNotFoundException;

public interface FlightScheduleService {
    FlightSchedule scheduleTrip(TripScheduleRequest tripScheduleRequest, long companyId);
//    FlightCancellationResponse cancelScheduledFlight(FlightCancellationRequest flightCancellationRequest);
    void deleteScheduledFlight(FlightSchedule flightScheduleToBeDeleted);
    FlightSchedule findById(long scheduleId) throws ScheduleNotFoundException;
}
