package AirlineApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightCancellationRequest {
    private String companyName;
    private String flightNumber;
    private String takeOffDay;
    private String takeOffMonth;
    private String takeOffYear;
    private String StartLocation;
}
