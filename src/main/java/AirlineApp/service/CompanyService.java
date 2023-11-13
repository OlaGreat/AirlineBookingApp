package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.FlightSchedule;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.request.UpdateScheduledFlightTripRequest;
import AirlineApp.dtos.response.*;
import AirlineApp.exceptions.AirlineException;

import java.util.List;

public interface CompanyService {
    CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException;
    FlightRegistrationResponse addFlight(AddFlightRequest addFlightRequest, Long id) throws AirlineException;
    FlightRemoveResponse removeFlight(String flightNumber) throws AirlineException;
    Company findById(Long id) throws AirlineException;

    TripScheduleResponse scheduleFlightTrip(TripScheduleRequest tripScheduleRequest, long companyId) throws AirlineException;
    List<FlightSchedule> viewScheduledFlight(long companyId) throws AirlineException;

    DeleteScheduledFlightResponse deleteScheduleFlight(long companyId, long scheduleFlightId) throws AirlineException;

//    UpdateScheduledTripResponse updateScheduledFlightTrip(UpdateScheduledFlightTripRequest request);

    Company findFirstOne();
}
