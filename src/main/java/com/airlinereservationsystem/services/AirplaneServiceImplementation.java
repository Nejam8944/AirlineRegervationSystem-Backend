package com.airlinereservationsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlinereservationsystem.entity.Airplane;
import com.airlinereservationsystem.repository.AirplaneRepository;

@Service
public class AirplaneServiceImplementation implements AirplaneService{

	@Autowired
	AirplaneRepository repo;
	
	@Override
	public Airplane addAirplane(Airplane airplane) {
		// TODO Auto-generated method stub
		return repo.save(airplane);
	}

	@Override
	public Airplane updateAirplane(Airplane airplane) {
		// TODO Auto-generated method stub
		return repo.save(airplane);
	}

	@Override
	public void deleteAirplane(String registrationNumber) {
		repo.deleteById(registrationNumber);

	}

	@Override
	public List<Airplane> getAllAirplane() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Airplane getAirplane(String airplaneName) {
		
		return repo.findByAirplaneName(airplaneName);
	}

}
