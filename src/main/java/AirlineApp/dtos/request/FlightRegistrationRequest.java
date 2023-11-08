package AirlineApp.dtos.request;

import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static AirlineApp.data.models.FlightStatus.IN_CABIN;

@Setter
@Getter
public class FlightRegistrationRequest {
    private String flightName;
    private String flightNumber;
    private FlightStatus flightStatus = IN_CABIN;
    private int flightCapacity;
    private List<String> destination;
}
