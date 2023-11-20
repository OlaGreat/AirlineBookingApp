package AirlineApp.controllers;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.FlightSearchRequest;
import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.exceptions.InvalidDateException;
import AirlineApp.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class PassengerController {


    private final PassengerService passengerService;

    @PostMapping("/register")
    public Object registerNewPassenger(@RequestBody RegisterPassengerRequest registerPassengerRequest){
        return passengerService.registerPassenger(registerPassengerRequest);
    }

    //TODO TEST WITH POSTMAN
    @GetMapping("/search")
    public ResponseEntity<List<FlightSchedule>> searchFlight(@RequestBody FlightSearchRequest request) throws InvalidDateException {
        List<FlightSchedule> foundFlight = passengerService.searchForFlight(request);
        return ResponseEntity.status(HttpStatus.OK).body(foundFlight);
    }

}
