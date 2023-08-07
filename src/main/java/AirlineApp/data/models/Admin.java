package AirlineApp.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "`admin`")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private User user;
}
