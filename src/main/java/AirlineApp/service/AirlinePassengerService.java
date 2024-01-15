package AirlineApp.service;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.Passenger;
import AirlineApp.data.models.User;
import AirlineApp.data.repositories.PassengerRepository;
import AirlineApp.data.repositories.UserRepository;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.dtos.response.RegisterPassengerResponse;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.exceptions.UserNotFoundException;
import AirlineApp.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static AirlineApp.data.models.Role.PASSENGER;
import static AirlineApp.dtos.response.ResponseMessage.USER_WITH_EMAIL_NOT_FOUND;
import static AirlineApp.dtos.response.ResponseMessage.YOU_SUCCESSFULLY_REGISTERED;

@Service
@AllArgsConstructor
@Slf4j
public class AirlinePassengerService implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final UserRepository userRepository;

    private final FlightScheduleService flightScheduleService;

    public RegisterPassengerResponse registerPassenger(RegisterPassengerRequest registerPassengerRequest) {
        Passenger passenger = new Passenger();
        User user = AppUtils.userMapper(registerPassengerRequest);
        user.setRole(PASSENGER);
        passenger.setUser(user);

        userRepository.save(user);
        passengerRepository.save(passenger);
        RegisterPassengerResponse registerPassengerResponse = new RegisterPassengerResponse();
        registerPassengerResponse.setMessage(YOU_SUCCESSFULLY_REGISTERED.getMessage());
        return registerPassengerResponse;
    }

    @Override
    public List<FlightSchedule> searchForFlight(FlightSearchRequest searchRequest) throws InvalidDateException {
        List<FlightSchedule> foundFlight = flightScheduleService.searchForFlight(searchRequest);
        return foundFlight;
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND.getMessage(), email)
        ));
        return user;
    }

//    @Override
//    public List<FlightSchedule> searchForFlightExtend(FlightSearchRequest searchRequest) throws InvalidDateException {
//        List<FlightSchedule> foundFlight = flightScheduleService.searchForFlightModified(searchRequest);
//        return foundFlight;
//    }


}
