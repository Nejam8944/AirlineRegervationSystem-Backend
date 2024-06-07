package com.airlinereservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlinereservationsystem.entity.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, String>{

	Airplane findByAirplaneName(String airplaneName);
}
