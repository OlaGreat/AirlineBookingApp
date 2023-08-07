package AirlineApp.data.repositories;

import AirlineApp.data.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
