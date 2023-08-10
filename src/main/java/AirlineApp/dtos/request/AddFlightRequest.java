package AirlineApp.dtos.request;

import AirlineApp.data.models.FlightDestination;
import AirlineApp.data.models.FlightStatus;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AddFlightRequest {
    private String flightName;
    private String flightNumber;
    private FlightStatus flightStatus = FlightStatus.IN_CABIN;
    private int flightCapacity;
    private FlightDestination flightDestinations;
    private String companyName;

}
