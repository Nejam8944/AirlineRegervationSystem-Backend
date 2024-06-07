package com.airlinereservationsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlinereservationsystem.entity.Airplane;
import com.airlinereservationsystem.entity.Flight;
import com.airlinereservationsystem.repository.FlightRepository;

@Service
public class FlightServiceImplementation implements FlightService {

	@Autowired
	FlightRepository repo;
	@Autowired
	AirplaneService airplaneService;
	
	@Override
	public Flight addFlight(Flight flight) {
    	initializeAvailableSeat(flight);
		return repo.save(flight);
	}

	@Override
	public Flight updateFlight(Flight flight) {
		return repo.save(flight);
	}

	@Override
	public void deleteFlight(String flightNumber) {
		repo.deleteById(flightNumber);
	}

	@Override
	public List<Flight> getAllFlight() {
		return repo.findAll();
	}

	@Override
	public void initializeAvailableSeat(Flight flight) {
		Airplane airplane = airplaneService.getAirplane(flight.getAirplane());
		flight.setAvailableSeatOfEconomyClass(airplane.getTotalEconomyClassSeat());
		flight.setAvailableSeatOfBusinessClass(airplane.getTotalBusinessClassSeat());
		flight.setAvailableSeatOfFirstClass(airplane.getTotalFirstClassSeat());
		
	}

	@Override
	public void updateAvailableSeat(Flight flight, String classType, Integer totalPassenger) {
		 if (classType == null || classType.isEmpty()) {
		        throw new IllegalArgumentException("Class type must not be null or empty");
		    }
		
	    switch (classType) {
	        case "Economy":
	            flight.setAvailableSeatOfEconomyClass(
	                flight.getAvailableSeatOfEconomyClass() - totalPassenger
	            );
	            break;
	        case "Business":
	            flight.setAvailableSeatOfBusinessClass(
	                flight.getAvailableSeatOfBusinessClass() - totalPassenger
	            );
	            break;
	        case "First Class":
	            flight.setAvailableSeatOfFirstClass(
	                flight.getAvailableSeatOfFirstClass() - totalPassenger
	            );
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid class type: " + classType);
	    }
	    repo.save(flight);
	}

}
