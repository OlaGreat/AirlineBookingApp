package AirlineApp.dtos.request;

import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class AddFlightRequest {
    private String flightName;
    private String flightNumber;
    private FlightStatus flightStatus = FlightStatus.IN_CABIN;
    private int flightCapacity;
    private List<String> destination;


}
