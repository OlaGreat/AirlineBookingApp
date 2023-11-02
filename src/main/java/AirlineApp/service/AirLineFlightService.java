package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Flight;
import AirlineApp.data.repositories.FlightRepository;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.exceptions.FlightNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static AirlineApp.exceptions.ExceptionMessages.FLIGHT_NOT_FOUND;

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

    @Override
    public void deleteFlight(String flightNumber) throws FlightNotFoundException {
        findByFlightNumber(flightNumber);
        flightRepository.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Flight findByFlightNumber(String flightNumber) throws FlightNotFoundException {
        Flight foundFlight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(()->new FlightNotFoundException(FLIGHT_NOT_FOUND.name()));
        return foundFlight;
    }
}
