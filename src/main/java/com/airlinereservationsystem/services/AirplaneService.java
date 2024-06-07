package com.airlinereservationsystem.services;

import java.util.List;

import com.airlinereservationsystem.entity.Airplane;

public interface AirplaneService {

	Airplane addAirplane(Airplane airplane);
	Airplane getAirplane(String airplaneName);
	Airplane updateAirplane(Airplane airplane);
	void deleteAirplane(String registrationNumber);
	List<Airplane> getAllAirplane();
}
