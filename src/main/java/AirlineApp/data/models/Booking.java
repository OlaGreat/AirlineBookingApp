package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String flightName;
    @Column(nullable = false)
    private Destination startLocation;
    @Column(nullable = false)
    private Destination destination;
    @Column(nullable = false)
    private LocalDate bookingDate;
    @Column(nullable = false)
    private int seatNumber;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TicketType ticketType;
    private String ticketNumber;


    @PrePersist
    private void generateTicketNumber(){
        ticketNumber = String.valueOf(UUID.randomUUID());
    }

}


