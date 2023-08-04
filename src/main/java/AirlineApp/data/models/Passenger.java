package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
}
