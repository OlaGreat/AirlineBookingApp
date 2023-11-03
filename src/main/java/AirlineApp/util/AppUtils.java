package AirlineApp.util;

import AirlineApp.data.models.Passenger;
import AirlineApp.data.models.User;
import AirlineApp.dtos.request.RegisterPassengerRequest;

public class AppUtils {
    public static String comma = ", ";
    public static String splash="/";
    public static String space =" ";
    public static User userMapper(RegisterPassengerRequest registerPassengerRequest) {
        User user = new User();
        user.setFirstName(registerPassengerRequest.getFirstName());
        user.setLastName(registerPassengerRequest.getLastName());
        user.setEmail(registerPassengerRequest.getEmail());
        user.setGender(registerPassengerRequest.getGender());
        user.setPhoneNumber(registerPassengerRequest.getPhoneNumber());
        user.setPassword(registerPassengerRequest.getPassword());
        return user;
    }
}
