package com.example.airlinebookingapp.data.repository;

import com.example.airlinebookingapp.data.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
