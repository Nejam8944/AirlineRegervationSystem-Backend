package com.airlinereservationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airlinereservationsystem.entity.Airport;
import com.airlinereservationsystem.services.AirportService;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    AirportService service;
    
    @PostMapping("/add")
    public Airport addAirport(@RequestBody Airport airport) {
        return service.addAirport(airport);
    }
    
    @GetMapping("/all")
    public List<Airport> getAllAirports() {
        return service.getAllAirport();
    }
    
}
