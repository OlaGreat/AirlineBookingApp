package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
    private String startLocation;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate bookingDate;
    @Column(nullable = false)
    private int seatNumber;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TicketType ticketType;

}
