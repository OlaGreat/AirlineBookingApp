package AirlineApp.data.repositories;

import AirlineApp.data.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

}
