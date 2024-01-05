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
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    private User user;
}
