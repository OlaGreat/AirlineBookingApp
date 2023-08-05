package AirlineApp.dtos.request;

import AirlineApp.data.models.Gender;
import lombok.Data;

@Data
public class RegisterPassengerRequest {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String password;
}
