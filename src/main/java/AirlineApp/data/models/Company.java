package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table
@Setter
@Getter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false, unique = true)
    private String companyLicencesNumber;

    private String location;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = STRING)
    private List<Destination> routes;

    @OneToMany(fetch = FetchType.EAGER)
    private List<FlightSchedule> schedules;

}
