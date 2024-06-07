package com.airlinereservationsystem.services;

import java.util.List;

import com.airlinereservationsystem.entity.Airport;

public interface AirportService {

	Airport addAirport(Airport airport);
	Airport updateAirport(Airport airport);
	void deleteAirport(String airportCode);
	List<Airport> getAllAirport();
}
