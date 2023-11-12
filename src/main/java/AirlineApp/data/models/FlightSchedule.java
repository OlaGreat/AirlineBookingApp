package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    private Long companyId;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> passengersEmail;


    @Override
    public boolean equals(Object obj){
        if(obj.getClass() == this.getClass() ){
            FlightSchedule schedule = (FlightSchedule) obj;
            return Objects.equals(this.getScheduleId(), schedule.getScheduleId());
        }
        return false;
    }
}
