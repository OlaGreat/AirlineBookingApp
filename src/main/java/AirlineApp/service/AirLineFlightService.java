package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Destination;
import AirlineApp.data.models.Flight;
import AirlineApp.data.repositories.FlightRepository;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.exceptions.FlightAlreadyRegisteredException;
import AirlineApp.exceptions.FlightNotFoundException;
import AirlineApp.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static AirlineApp.dtos.response.ResponseMessage.FLIGHT_REGISTERED_SUCCESSFULLY;
import static AirlineApp.exceptions.ExceptionMessages.FLIGHT_ALREADY_EXIST;
import static AirlineApp.exceptions.ExceptionMessages.FLIGHT_NOT_FOUND;
import static AirlineApp.util.AppUtils.destination;

@AllArgsConstructor
@Service
public class AirLineFlightService implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightRegistrationResponse registerFlight(FlightRegistrationRequest flightRegistrationRequest, Company company) throws FlightNotFoundException, FlightAlreadyRegisteredException {
        String flightNumber = flightRegistrationRequest.getFlightNumber();
        if(isAlreadyRegistered(flightNumber)) throw new FlightAlreadyRegisteredException(FLIGHT_ALREADY_EXIST.getMessage());
        Flight flight = new Flight();
        flight.setCompany(company);
        List<Destination> destinations = destination(flightRegistrationRequest.getDestination());
        flight.setDestinations(destinations);
        BeanUtils.copyProperties(flightRegistrationRequest,flight);
        flightRepository.save(flight);

        FlightRegistrationResponse response = new FlightRegistrationResponse();
        response.setMessage(FLIGHT_REGISTERED_SUCCESSFULLY.getMessage());
        return response;
    }

    @Override
    public void deleteFlight(String flightNumber) throws FlightNotFoundException {
        System.out.println("------------------>>> ");
        Flight flight = findByFlightNumber(flightNumber);
        flightRepository.delete(flight);

    }


    @Override
    public Flight findByFlightNumber(String flightNumber) throws FlightNotFoundException {
        Flight foundFlight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(()->new FlightNotFoundException(FLIGHT_NOT_FOUND.name()));
        return foundFlight;
    }

    private boolean isAlreadyRegistered(String flightNumber) {
        Optional<Flight> foundFlight = flightRepository.findByFlightNumber(flightNumber);
        return foundFlight.isPresent();
    }


}
