package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.mapping.FetchProfile;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = STRING)
    private List<Destination> routes;

}
