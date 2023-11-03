package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.FlightType;
import AirlineApp.data.repositories.CompanyRepository;
import AirlineApp.dtos.request.AddFlightRequest;
import AirlineApp.dtos.request.CompanyRegistrationRequest;
import AirlineApp.dtos.request.FlightRegistrationRequest;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.CompanyRegistrationResponse;
import AirlineApp.dtos.response.FlightRegistrationResponse;
import AirlineApp.dtos.response.FlightRemoveResponse;
import AirlineApp.dtos.response.TripScheduleResponse;
import AirlineApp.exceptions.AirlineException;
import AirlineApp.exceptions.FlightNotFoundException;
import AirlineApp.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static AirlineApp.dtos.response.ResponseMessage.*;
import static AirlineApp.exceptions.ExceptionMessages.*;
import static AirlineApp.util.AppUtils.*;


@Service
@AllArgsConstructor
public class AirlineCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final FlightService flightService;

    @Override
    public CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException {
        verifyCompanyRegistrationRequest(companyRegistrationRequest);
        Company company = new Company();
        BeanUtils.copyProperties(companyRegistrationRequest,company);
        Company savedCompany = companyRepository.save(company);

        CompanyRegistrationResponse companyRegistrationResponse = new CompanyRegistrationResponse();
        companyRegistrationResponse.setMessage(WELCOME_ONBOARD.getMessage()+savedCompany.getCompanyName()+WE_LOOK_FORWARD_TO_A_WONDERFUL_PARTNERSHIP.getMessage());
        return companyRegistrationResponse;
    }

    @Override
    public FlightRegistrationResponse addFlight(AddFlightRequest addFlightRequest, Long id) throws AirlineException {
        FlightRegistrationRequest flightRegistrationRequest = new FlightRegistrationRequest();
        BeanUtils.copyProperties(addFlightRequest, flightRegistrationRequest);

        Company company = findById(id);
        FlightRegistrationResponse flightRegistrationResponse = flightService.registerFlight(flightRegistrationRequest, company);
        return flightRegistrationResponse;
    }

    @Override
    public FlightRemoveResponse removeFlight(String flightNumber) throws FlightNotFoundException {
        flightService.deleteFlight(flightNumber);

        FlightRemoveResponse response = new FlightRemoveResponse();
        response.setMessage(FLIGHT.getMessage() + flightNumber + SUCCESSFULLY_REMOVED);
        return response;
    }


    @Override
    public Company findById(Long id) throws AirlineException {
        Company company = companyRepository.findById(id)
                                           .orElseThrow(()->new AirlineException(COMPANY_NOT_FOUND.getMessage()));
        return company;
    }

    @Override
    public TripScheduleResponse scheduleFlightTrip(TripScheduleRequest tripScheduleRequest, Long companyId) {
        FlightSchedule flightSchedule = new FlightSchedule();
        flightSchedule.setFlightPriceBusinessClass(new BigDecimal(tripScheduleRequest.getFlightPriceBusinessClass()));
        flightSchedule.setFlightPriceEconomyClass(new BigDecimal(tripScheduleRequest.getFlightPriceEconomyClass()));
        flightSchedule.setDestination(Destination.valueOf(tripScheduleRequest.getDestination().toUpperCase()));
        flightSchedule.setStartLocation(Destination.valueOf(tripScheduleRequest.getStartLocation().toUpperCase()));
        flightSchedule.setFlightType(FlightType.valueOf(tripScheduleRequest.getFlightType().toUpperCase()));
        BeanUtils.copyProperties(tripScheduleRequest, flightSchedule);

        TripScheduleResponse scheduleResponse = new TripScheduleResponse();
        scheduleResponse.setMessage(TRIP_SCHEDULE_FOR_FLIGHT.getMessage()+tripScheduleRequest.getFlightName()+comma+ON.getMessage()+
                tripScheduleRequest.getTakeOffDay()+splash+ tripScheduleRequest.getTakeOffMonth()+splash+
                tripScheduleRequest.getTakeOffYear()+space+tripScheduleRequest.getTakeOffTime()+space+IS_SUCCESSFUL);
        return scheduleResponse;
    }

    private static void verifyTripScheduleRequest(TripScheduleRequest tripScheduleRequest){
        Field[] field = tripScheduleRequest.getClass().getFields();
        System.out.println(Arrays.toString(field));
    }

    private static void verifyCompanyRegistrationRequest(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException {
        if(companyRegistrationRequest.getCompanyName() == null)throw new AirlineException(PLEASE_PROVIDE_YOUR_COMPANY_NAME.getMessage());
        if (companyRegistrationRequest.getCompanyLicencesNumber() == null) throw new AirlineException(PLEASE_PROVIDE_YOUR_LICENCES_NUMBER.getMessage());
    }
}
