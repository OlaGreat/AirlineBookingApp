package AirlineApp.data.repositories;

import AirlineApp.data.models.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
}
