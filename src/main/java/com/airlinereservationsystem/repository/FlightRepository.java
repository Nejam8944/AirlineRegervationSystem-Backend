package com.airlinereservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airlinereservationsystem.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{

}
