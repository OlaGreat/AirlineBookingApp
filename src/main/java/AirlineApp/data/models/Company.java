package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String companyName;


    @Column(nullable = false, unique = true)
    private String companyLicencesNumber;

    private String location;

    @OneToMany
    private List<Passenger> customers;

    @ElementCollection
    private List<Destination> route;

}
