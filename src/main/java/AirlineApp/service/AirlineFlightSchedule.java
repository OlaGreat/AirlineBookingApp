package AirlineApp.service;

import AirlineApp.data.models.Company;
import AirlineApp.data.models.Destination;
import AirlineApp.data.models.FlightSchedule;
import AirlineApp.data.models.FlightType;
import AirlineApp.data.repositories.FlightScheduleRepository;
import AirlineApp.dtos.request.TripScheduleRequest;
import AirlineApp.dtos.response.TripScheduleResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static AirlineApp.dtos.response.ResponseMessage.*;
import static AirlineApp.util.AppUtils.*;
import static AirlineApp.util.AppUtils.space;

@Service
@AllArgsConstructor
public class AirlineFlightSchedule implements FlightScheduleService{

    private final FlightScheduleRepository flightScheduleRepository;



    @Override
    public FlightSchedule scheduleTrip(TripScheduleRequest tripScheduleRequest, Company company) {
        FlightSchedule flightSchedule = flightScheduleMapper(tripScheduleRequest, company);
        FlightSchedule savedFlightSchedule = flightScheduleRepository.save(flightSchedule);

       return savedFlightSchedule;
    }
}
