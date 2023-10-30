package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Flight;
import AirlineApp.data.repositories.FlightRepository;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AirLineFlightService implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightRegistrationResponse registerFlight(FlightRegistrationRequest flightRegistrationRequest, Company company) {
        Flight flight = new Flight();
        flight.setCompany(company);
        BeanUtils.copyProperties(flightRegistrationRequest,flight);
        flightRepository.save(flight);

        FlightRegistrationResponse response = new FlightRegistrationResponse();
        response.setMessage("Flight registered successfully");
        return response;
    }
}
