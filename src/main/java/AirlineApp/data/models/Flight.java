
package AirlineApp.data.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Flight")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long flightId;

    @Column(nullable = false)
    private String flightName;

    @Column(nullable = false, unique = true)
    private String flightNumber;


    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @Column(nullable = false)
    private int flightCapacity;

    @ElementCollection
    private List<Destination> destinations;

    @ManyToOne
    private Company company;

}
