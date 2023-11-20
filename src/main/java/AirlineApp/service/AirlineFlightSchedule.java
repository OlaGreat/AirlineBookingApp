package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.repositories.FlightScheduleRepository;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.ScheduleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static AirlineApp.dtos.response.ResponseMessage.FLIGHT_SCHEDULE_NOT_FOUND;
import static AirlineApp.util.AppUtils.*;

@Service
@AllArgsConstructor
public class AirlineFlightSchedule implements FlightScheduleService{

    private final FlightScheduleRepository flightScheduleRepository;



    @Override
    public FlightSchedule scheduleTrip(TripScheduleRequest tripScheduleRequest, long companyId) throws InvalidDateException {
        verifyRequestDate(tripScheduleRequest.getTakeOffDay(), tripScheduleRequest.getTakeOffMonth(), tripScheduleRequest.getTakeOffYear());
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
    public List<FlightSchedule> searchForFlight(FlightSearchRequest searchRequest) throws InvalidDateException {
        String takeOffDay = searchRequest.getTakeOffDay();
        String takeOffMonth = searchRequest.getTakeOffMonth().toUpperCase();
        String takeOffYear = searchRequest.getTakeOffYear();
        verifyRequestDate(takeOffDay, searchRequest.getTakeOffMonth(), takeOffYear);

       List<FlightSchedule> foundSchedule = fetchAndFilterFlightSchedule(takeOffDay, takeOffMonth, takeOffYear);

       return foundSchedule;
    }

    @Override
    public List<FlightSchedule> searchForFlightModified(FlightSearchRequest searchRequest) {

        return null;
    }

    private List<FlightSchedule> fetchAndFilterFlightSchedule(String takeOffDay, String takeOffMonth, String takeOffYear){
        List<FlightSchedule> allSchedule = flightScheduleRepository.findAll();
        List<FlightSchedule> searchMatch = allSchedule.stream()
                .filter(schedule -> schedule.getTakeOffDay().equalsIgnoreCase(takeOffDay)
                        && schedule.getTakeOffMonth().equalsIgnoreCase(takeOffMonth)
                        && schedule.getTakeOffYear().equalsIgnoreCase(takeOffYear)
                        && LocalTime.now().plusMinutes(thirtyMinute)
                        .isBefore(LocalTime.parse(schedule.getTakeOffTime()))
                        && schedule.getPassengersEmail().size() < schedule.getFlightCapacity()
                ).toList();

        return searchMatch;
    }







    private FlightSchedule findScheduledFlightById(long scheduledFlightId) throws ScheduleNotFoundException {
        FlightSchedule foundScheduledFlight = flightScheduleRepository.findById(scheduledFlightId)
                .orElseThrow(()-> new ScheduleNotFoundException(FLIGHT_SCHEDULE_NOT_FOUND.getMessage()));

        return foundScheduledFlight;
    }
}
