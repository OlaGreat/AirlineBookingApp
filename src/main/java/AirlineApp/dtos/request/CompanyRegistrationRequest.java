package AirlineApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRegistrationRequest {
    private String companyName;
    private String companyLicencesNumber;
    private String location;

}
