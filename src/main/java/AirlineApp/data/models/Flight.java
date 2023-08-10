
package AirlineApp.data.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Flight")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String flightName;

    @Column(nullable = false)
    private String flightNumber;


    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @Column(nullable = false)
    private int flightCapacity;

    @ElementCollection
    private List<FlightDestination> flightDestinations;

    @Column(nullable = false)
    private String companyName;

}
