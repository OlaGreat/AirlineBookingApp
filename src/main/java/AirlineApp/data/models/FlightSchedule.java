package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;
    @Column(nullable = false)
    private String flightName;
    @Column(nullable = false)
    private int flightCapacity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Destination startLocation;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Destination destination;
    @Column(nullable = false)
    private BigDecimal flightPriceBusinessClass;
    @Column(nullable = false)
    private BigDecimal flightPriceEconomyClass;
    @Column(nullable = false)
    private String takeOffMonth;
    @Column(nullable = false)
    private String takeOffDay;
    @Column(nullable = false)
    private String takeOffYear;
    @Column(nullable = false)
    private String takeOffTime;
    @Column(nullable = false)
    private String landingMonth;
    @Column(nullable = false)
    private String landingDay;
    @Column(nullable = false)
    private String landingYear;
    @Column(nullable = false)
    private String landingTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightType flightType;
    @ManyToOne
    private Company company;
    @ElementCollection
    private List<String> passengersEmail;
}
