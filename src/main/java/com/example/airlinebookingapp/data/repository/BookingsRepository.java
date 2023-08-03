package com.example.airlinebookingapp.data.repository;

import com.example.airlinebookingapp.data.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

}
