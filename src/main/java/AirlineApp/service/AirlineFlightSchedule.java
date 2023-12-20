package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.repositories.FlightScheduleRepository;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.ScheduleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        System.out.println(savedFlightSchedule);

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
        int takeOffDay = searchRequest.getTakeOffDay();
        String takeOffMonth = searchRequest.getTakeOffMonth().toUpperCase();
        String takeOffYear = searchRequest.getTakeOffYear();
        verifyRequestDate(takeOffDay, searchRequest.getTakeOffMonth(), takeOffYear);

       List<FlightSchedule> foundSchedule = fetchAndFilterFlightSchedule(takeOffDay, takeOffMonth, takeOffYear);

       return foundSchedule;
    }

//    @Override
//    public List<FlightSchedule> searchForFlightModified(FlightSearchRequest searchRequest) throws InvalidDateException {
//        List<FlightSchedule> allFlight = flightScheduleRepository.findAll();
//
//        int takeOffDay = searchRequest.getTakeOffDay();
//        String takeOffMonth = searchRequest.getTakeOffMonth().toUpperCase();
//        String takeOffYear = searchRequest.getTakeOffYear();
//        verifyRequestDate(takeOffDay, searchRequest.getTakeOffMonth(), takeOffYear);
//
//        Month month = Month.valueOf(takeOffMonth);
//        int monthLength = month.length(isLeapYear(Integer.parseInt(takeOffYear)));
//        int overLappingDays = monthLength - takeOffDay;
//        if ( overLappingDays < 10){
//            int overLap = 10 - overLappingDays;
//            Month nextMonth = month.plus(1);
//        }
//
//        else {
//            int day = takeOffDay + 10;
//
//
//        }
//
////        allFlight.stream()
//
//        return null;
//    }





//    public static void main(String[] args) {
//       List<Integer> num = IntStream.rangeClosed(1, 10).boxed().toList();
//
//       num.forEach(System.out::println);
//
////        Month m = Month.valueOf("NOVEMBER");
////        System.out.println(m.maxLength());
////        System.out.println(m.plus(1));
////
////        System.out.println(isLeapYear(2100));
//    }

    private static boolean isLeapYear(int year){
        boolean  isLeapYear = year%4 == 0 && year%100!=0 || year%400==0;
        return isLeapYear;
    }


    private List<FlightSchedule> fetchAndFilterFlightSchedule(int takeOffDay, String takeOffMonth, String takeOffYear){
        List<FlightSchedule> allSchedule = flightScheduleRepository.findAll();
        List<FlightSchedule> searchMatch = allSchedule.stream()
                .filter(schedule -> schedule.getTakeOffYear().equalsIgnoreCase(takeOffYear)
                        && schedule.getTakeOffMonth().equalsIgnoreCase(takeOffMonth)
                        && schedule.getTakeOffDay() >= takeOffDay
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
