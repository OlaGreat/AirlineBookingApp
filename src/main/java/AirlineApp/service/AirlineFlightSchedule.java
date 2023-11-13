package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.repositories.FlightScheduleRepository;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.ScheduleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void deleteScheduledFlight(FlightSchedule flightScheduleToBeDeleted) {
        flightScheduleRepository.delete(flightScheduleToBeDeleted);
    }

    @Override
    public FlightSchedule findById(long scheduleId) throws ScheduleNotFoundException {
        FlightSchedule foundFlightSchedule = flightScheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new ScheduleNotFoundException(FLIGHT_SCHEDULE_NOT_FOUND.getMessage()));
        return foundFlightSchedule;
    }

    @Override
    public List<FlightSchedule> searchForFlight(FlightSearchRequest searchRequest) {
        String takeOffDay = searchRequest.getTakeOffDay().toUpperCase();
        String takeOffMonth = searchRequest.getTakeOffMonth().toUpperCase();
        String takeOffYear = searchRequest.getTakeOffYear().toUpperCase();

       List<FlightSchedule> foundSchedule = fetchAndFilterFlightSchedule(takeOffDay, takeOffMonth, takeOffYear);

       return foundSchedule;
    }

    private List<FlightSchedule> fetchAndFilterFlightSchedule(String takeOffDay, String takeOffMonth, String takeOffYear){
        List<FlightSchedule> allSchedule = flightScheduleRepository.findAll();
        List<FlightSchedule> searchMatch = allSchedule.stream()
                .filter(schedule -> schedule.getTakeOffDay().equals(takeOffDay)
                        && schedule.getTakeOffMonth().equals(takeOffMonth)
                        && schedule.getTakeOffYear().equals(takeOffYear)).toList();

        return searchMatch;
    }

    private FlightSchedule findScheduledFlightById(long scheduledFlightId) throws ScheduleNotFoundException {
        FlightSchedule foundScheduledFlight = flightScheduleRepository.findById(scheduledFlightId)
                .orElseThrow(()-> new ScheduleNotFoundException(FLIGHT_SCHEDULE_NOT_FOUND.getMessage()));

        return foundScheduledFlight;
    }
}
