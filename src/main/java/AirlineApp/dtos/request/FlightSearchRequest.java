package AirlineApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightSearchRequest {
    private int takeOffDay;
    private String takeOffMonth;
    private String takeOffYear;
}
