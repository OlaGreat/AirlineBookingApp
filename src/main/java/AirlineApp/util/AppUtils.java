package AirlineApp.util;

import AirlineApp.data.models.*;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class AppUtils {
    public static String comma = ", ";
    public static String splash="/";
    public static String space =" ";


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

    public static FlightSchedule flightScheduleMapper(TripScheduleRequest tripScheduleRequest, Company company){
       FlightSchedule flightSchedule = new FlightSchedule();
        flightSchedule.setCompany(company);
        flightSchedule.setFlightPriceBusinessClass(new BigDecimal(tripScheduleRequest.getFlightPriceBusinessClass()));
        flightSchedule.setFlightPriceEconomyClass(new BigDecimal(tripScheduleRequest.getFlightPriceEconomyClass()));
        flightSchedule.setDestination(Destination.valueOf(tripScheduleRequest.getDestination().toUpperCase()));
        flightSchedule.setStartLocation(Destination.valueOf(tripScheduleRequest.getStartLocation().toUpperCase()));
        flightSchedule.setFlightType(FlightType.valueOf(tripScheduleRequest.getFlightType().toUpperCase()));
        BeanUtils.copyProperties(tripScheduleRequest, flightSchedule);
        return flightSchedule;
    }
}
