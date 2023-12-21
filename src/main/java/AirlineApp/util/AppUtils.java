
package AirlineApp.util;

import AirlineApp.data.models.*;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.exceptions.InvalidDateException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static AirlineApp.exceptions.ExceptionMessages.INVALID_DATE_DATE_CANNOT_BE_BEFORE_TODAY_DATE_PLEASE_CHECK_AND_CORRECT_DATE;

public class AppUtils {
    public static final String USER = "user";
    public static final String APP_NAME = "JpBookings";
    public static final String SECRET_KEY = "JpBookingsSecret";
    public static final String BEARER = "Bearer";
    public static final int SEVEN = 7;
    public static String comma = ", ";
    public static String splash="/";
    public static String space =" ";
    public static String dash = "-";

    public static Long thirtyMinute = 30L;


    public static List<Destination> destination (List<String> destinations){
        List<Destination> routes = destinations.stream()
                .map(destination -> Destination.valueOf(destination.toUpperCase()))
                 .collect(Collectors.toList());

        return routes;
    }
    public static User userMapper(RegisterPassengerRequest registerPassengerRequest) {
        User user = new User();
        user.setFirstName(registerPassengerRequest.getFirstName());
        user.setLastName(registerPassengerRequest.getLastName());
        user.setEmail(registerPassengerRequest.getEmail());
        user.setGender(registerPassengerRequest.getGender());
        user.setPhoneNumber(registerPassengerRequest.getPhoneNumber());
        user.setPassword(registerPassengerRequest.getPassword());
        return user;
    }

    public static FlightSchedule flightScheduleMapper(TripScheduleRequest tripScheduleRequest, Long companyId){
       FlightSchedule flightSchedule = new FlightSchedule();
        flightSchedule.setCompanyId(companyId);
        flightSchedule.setFlightPriceBusinessClass(new BigDecimal(tripScheduleRequest.getFlightPriceBusinessClass()));
        flightSchedule.setFlightPriceEconomyClass(new BigDecimal(tripScheduleRequest.getFlightPriceEconomyClass()));
        flightSchedule.setDestination(Destination.valueOf(tripScheduleRequest.getDestination().toUpperCase()));
        flightSchedule.setStartLocation(Destination.valueOf(tripScheduleRequest.getStartLocation().toUpperCase()));
        flightSchedule.setFlightType(FlightType.valueOf(tripScheduleRequest.getFlightType().toUpperCase()));
        BeanUtils.copyProperties(tripScheduleRequest, flightSchedule);
        return flightSchedule;
    }

    public static String processDate(int day, String month, String year) {
        StringBuilder date = new StringBuilder();
        date.append(year)
          .append(dash)
          .append(month)
          .append(dash)
          .append(day);

        return String.valueOf(date);
    }

    public static void verifyRequestDate(int day, String month, String year) throws InvalidDateException {
        String date = processDate(day, month, year);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MMMM-dd");
        LocalDate inputDate = LocalDate.parse(date, formatDate);
        if (inputDate.isBefore(LocalDate.now())) throw new InvalidDateException(INVALID_DATE_DATE_CANNOT_BE_BEFORE_TODAY_DATE_PLEASE_CHECK_AND_CORRECT_DATE.getMessage());

    }
}

