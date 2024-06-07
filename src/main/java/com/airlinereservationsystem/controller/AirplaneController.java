package com.airlinereservationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airlinereservationsystem.entity.Airplane;
import com.airlinereservationsystem.services.AirplaneService;


@RestController
@RequestMapping("/airplane")
public class AirplaneController {

	@Autowired
	AirplaneService service;
	
	@PostMapping("/add")
	public Airplane addAirplane(@RequestBody Airplane airplane) {
		return service.addAirplane(airplane);
	}
	
	@GetMapping("/all")
	public List<Airplane> getAllAirplane() {
		return service.getAllAirplane();
	}
	
	@GetMapping("/get/{airplaneName}")
	public Airplane getAirplane(@PathVariable String airplaneName) {
		return service.getAirplane(airplaneName);
	}
	
}
