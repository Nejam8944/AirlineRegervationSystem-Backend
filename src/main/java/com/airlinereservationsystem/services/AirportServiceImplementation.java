package com.airlinereservationsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlinereservationsystem.entity.Airport;
import com.airlinereservationsystem.repository.AirportRepository;

@Service
public class AirportServiceImplementation implements AirportService {

	@Autowired
	AirportRepository repo;
	
	@Override
	public Airport addAirport(Airport airport) {
		// TODO Auto-generated method stub
		return repo.save(airport);
	}

	@Override
	public Airport updateAirport(Airport airport) {
		// TODO Auto-generated method stub
		return repo.save(airport);
	}

	@Override
	public void deleteAirport(String airportCode) {
		// TODO Auto-generated method stub
		repo.deleteById(airportCode);
	}

	@Override
	public List<Airport> getAllAirport() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
