package AirlineApp.controllers;

import AirlineApp.dtos.request.RegisterPassengerRequest;
import AirlineApp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/register")
    public Object registerNewPassenger(@RequestBody RegisterPassengerRequest registerPassengerRequest){
        return passengerService.registerPassenger(registerPassengerRequest);
    }
}
