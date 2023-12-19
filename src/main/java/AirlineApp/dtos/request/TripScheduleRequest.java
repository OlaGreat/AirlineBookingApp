package AirlineApp.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class TripScheduleRequest {
    private String flightName;
    private int flightCapacity;
    private String startLocation;
    private String destination;
    private String flightPriceBusinessClass;
    private String flightPriceEconomyClass;
    private String takeOffYear;
    private String takeOffMonth;
    private int takeOffDay;
    private String takeOffTime;
    private String landingYear;
    private String landingMonth;
    private String landingDay;
    private String landingTime;
    private String flightType;
}
