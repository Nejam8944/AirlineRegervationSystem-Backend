package com.airlinereservationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airlinereservationsystem.entity.Airplane;
import com.airlinereservationsystem.entity.Flight;
import com.airlinereservationsystem.services.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService service;
    
    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight flight) {
        return service.addFlight(flight);
    }
    
    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return service.getAllFlight();
    }
    
    @PutMapping("/update")
    public Flight updateFlight(@RequestBody Flight flight) {
        return service.updateFlight(flight);
    }
    
    @DeleteMapping("/delete/{flightNumber}")
    public void deleteFlight(@PathVariable String flightNumber) {
    	service.deleteFlight(flightNumber);
    }
    
    @PutMapping("/update/availableSeat")
    public void updateAvailableSeat(@RequestBody Flight flight, 
                                    @RequestParam String classType, 
                                    @RequestParam int totalPassengers) {
        service.updateAvailableSeat(flight, classType, totalPassengers);
    }

}
