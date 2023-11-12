package AirlineApp.dtos.request;

import AirlineApp.data.models.Destination;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class UpdateScheduledFlightTripRequest {
    private String flightName;
    private int flightCapacity;
    private Destination startLocation;
    private Destination destination;
    private BigDecimal flightPriceBusinessClass;
    private BigDecimal flightPriceEconomyClass;
    private String takeOffMonth;
    private String takeOffDay;
    private String takeOffYear;
    private String takeOffTime;
    private String landingMonth;
    private String landingDay;
    private String landingYear;
    private String landingTime;
}
