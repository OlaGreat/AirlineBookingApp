package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.repositories.FlightScheduleRepository;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.ScheduleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static AirlineApp.dtos.response.ResponseMessage.FLIGHT_SCHEDULE_NOT_FOUND;
import static AirlineApp.util.AppUtils.flightScheduleMapper;

@Service
@AllArgsConstructor
public class AirlineFlightSchedule implements FlightScheduleService{

    private final FlightScheduleRepository flightScheduleRepository;



    @Override
    public FlightSchedule scheduleTrip(TripScheduleRequest tripScheduleRequest, long companyId) {
        FlightSchedule flightSchedule = flightScheduleMapper(tripScheduleRequest, companyId);
        FlightSchedule savedFlightSchedule = flightScheduleRepository.save(flightSchedule);

       return savedFlightSchedule;
    }

    @Override
    public FlightSchedule deleteScheduledFlight(long scheduledFlightId) throws ScheduleNotFoundException {
        FlightSchedule foundFlightSchedule = findScheduledFlightById(scheduledFlightId);
        flightScheduleRepository.deleteById(scheduledFlightId);
        return foundFlightSchedule;
    }

    @Override
    public FlightSchedule findById(long scheduleId) throws ScheduleNotFoundException {
        FlightSchedule foundFlightSchedule = flightScheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new ScheduleNotFoundException(FLIGHT_SCHEDULE_NOT_FOUND.getMessage()));
        return foundFlightSchedule;
    }

    private FlightSchedule findScheduledFlightById(long scheduledFlightId) throws ScheduleNotFoundException {
        FlightSchedule foundScheduledFlight = flightScheduleRepository.findById(scheduledFlightId)
                .orElseThrow(()-> new ScheduleNotFoundException(FLIGHT_SCHEDULE_NOT_FOUND.getMessage()));

        return foundScheduledFlight;
    }
}
