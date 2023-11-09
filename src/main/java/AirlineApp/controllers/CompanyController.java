package AirlineApp.controllers;

import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.dtos.response.FlightRemoveResponse;
import AirlineApp.dtos.response.TripScheduleResponse;
import AirlineApp.exceptions.AirlineException;
import AirlineApp.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;


    @PostMapping
    ResponseEntity<CompanyRegistrationResponse> registerCompany (@RequestBody CompanyRegistrationRequest request) throws AirlineException {
        CompanyRegistrationResponse response = companyService.registerCompany(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-flight/{companyId}")
    ResponseEntity<FlightRegistrationResponse> addFlight (@RequestBody AddFlightRequest request, @PathVariable Long companyId) throws AirlineException {
        FlightRegistrationResponse response = companyService.addFlight(request, companyId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/remove-flight/{flightNumber}")
    ResponseEntity<FlightRemoveResponse> deleteFlight(@PathVariable String flightNumber) throws AirlineException {
        FlightRemoveResponse response = companyService.removeFlight(flightNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/schedule-flight/{companyId}")
    ResponseEntity<TripScheduleResponse> scheduleTrip(@RequestBody TripScheduleRequest request, @PathVariable Long companyId) throws AirlineException {
        TripScheduleResponse response = companyService.scheduleFlightTrip(request, companyId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/veiw-scheduled-flight/{companyId}")
    ResponseEntity<List<FlightSchedule>> viewScheduledFlight(@PathVariable Long companyId) throws AirlineException {
        List<FlightSchedule> scheduledFlight = companyService.viewScheduledFlight(companyId);
        return ResponseEntity.status(HttpStatus.OK).body(scheduledFlight);
    }

}
