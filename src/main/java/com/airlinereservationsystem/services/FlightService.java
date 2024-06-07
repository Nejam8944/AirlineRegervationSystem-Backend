package com.airlinereservationsystem.services;

import java.util.List;

import com.airlinereservationsystem.entity.Flight;

public interface FlightService {

	Flight addFlight(Flight flight);
	Flight updateFlight(Flight flight);
	void updateAvailableSeat(Flight flight ,String classType,Integer totalPassanger);
	void deleteFlight(String flightNumber);
	void initializeAvailableSeat(Flight flight);
	List<Flight> getAllFlight();
}
