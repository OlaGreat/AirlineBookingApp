package AirlineApp.service;


import AirlineApp.dtos.request.BookingRequest;
import AirlineApp.dtos.response.BookingResponse;

public interface BookingService {
    BookingResponse bookFlight(BookingRequest request, String userId);

}
