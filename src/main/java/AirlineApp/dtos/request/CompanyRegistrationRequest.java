package AirlineApp.dtos.request;

import AirlineApp.data.models.Destination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyRegistrationRequest {
    private String companyName;
    private String companyLicencesNumber;
    private String location;
    private List<Destination> routes;
}
