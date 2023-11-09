package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightSchedule;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static AirlineApp.dtos.response.ResponseMessage.*;
import static AirlineApp.exceptions.ExceptionMessages.*;
import static AirlineApp.util.AppUtils.*;
import static AirlineApp.util.AppUtils.space;


@Service
@AllArgsConstructor
public class AirlineCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final FlightService flightService;

    private final FlightScheduleService flightScheduleService;

    @Override
    public CompanyRegistrationResponse registerCompany(CompanyRegistrationRequest companyRegistrationRequest) throws AirlineException {
        verifyCompanyRegistrationRequest(companyRegistrationRequest);
        Company company = new Company();

        List<Destination> routes = destination(companyRegistrationRequest.getRoutes());
        company.setRoutes(routes);

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
        response.setMessage(FLIGHT.getMessage() + flightNumber + SUCCESSFULLY_REMOVED.getMessage());
        return response;
    }


    @Override
    public Company findById(Long id) throws AirlineException {
        Company company = companyRepository.findById(id)
                                           .orElseThrow(()->new AirlineException(COMPANY_NOT_FOUND.getMessage()));
        return company;
    }

    @Override
    public TripScheduleResponse scheduleFlightTrip(TripScheduleRequest tripScheduleRequest, long companyId) throws AirlineException {
        Company company = findById(companyId);
        FlightSchedule scheduledFlight = flightScheduleService.scheduleTrip(tripScheduleRequest,companyId);

        company.getSchedules().add(scheduledFlight);
        companyRepository.save(company);

        TripScheduleResponse tripScheduleResponse = new TripScheduleResponse();
        tripScheduleResponse.setMessage(TRIP_SCHEDULE_FOR_FLIGHT.getMessage()+scheduledFlight.getFlightName()+comma+FOR.getMessage()+
                scheduledFlight.getTakeOffDay()+splash+ scheduledFlight.getTakeOffMonth()+splash+
                scheduledFlight.getTakeOffYear()+space+scheduledFlight.getTakeOffTime()+space+IS_SUCCESSFUL);

       return tripScheduleResponse;
    }

    @Override
    public List<FlightSchedule> viewScheduledFlight(long companyId) throws AirlineException {
        Company company = findById(companyId);
        List<FlightSchedule> scheduledTrip = company.getSchedules();
        return scheduledTrip;
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
