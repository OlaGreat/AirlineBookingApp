package com.example.airlinebookingapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @Column(nullable = false)
    private int flightCapacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightDestination flightDestination;

}
